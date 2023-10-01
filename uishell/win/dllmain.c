/* Replace "dll.h" with the name of your header */
#include "dll.h"
#include "WindowCompositionAttribute.h"
#include <dwmapi.h>
#include <windows.h>
#include <stdio.h>
#include <stdlib.h>
#include <wingdi.h>

DLLIMPORT void implNativeWarning() {
    MessageBox(0, "Backdrop set failed, please check your console for resolution. \n", "Backdrop setting failed", MB_ICONWARNING);
}

DLLIMPORT void implHideBar(HWND hWnd) {
    LONG_PTR Style = GetWindowLongPtr(hWnd,GWL_STYLE);
    // Style = Style &~WS_CAPTION &~WS_SYSMENU &~WS_THICKFRAME;
    SetWindowLongPtr(hWnd, GWL_STYLE, Style);
}

DLLIMPORT DWORD implGetCompositionColor() {
  DWORD color = 0;
  BOOL opaque = FALSE;
  
  HRESULT hr = DwmGetColorizationColor(&color, &opaque);
  if (hr == S_OK) return color;
  else {
    HKEY hk;
    LONG lReturn = RegOpenKeyEx(HKEY_CURRENT_USER, "Software\\Microsoft\\Windows\\DWM", 0, KEY_READ, &hk);
    if (ERROR_SUCCESS == lReturn) {
      DWORD dwValue;
      DWORD dwSize = sizeof(dwValue);
      lReturn = RegQueryValueEx(hk, "AccentColor", NULL, NULL, (LPBYTE)&dwValue, &dwSize);
      RegCloseKey(hk);
      if (ERROR_SUCCESS == lReturn) {
        DWORD b = (dwValue >> 16) & 0xFF;
        DWORD g = (dwValue >> 8) & 0xFF;
        DWORD r = (dwValue >> 0) & 0xFF;
        DWORD a = 255;

        return ((a & 0xFF) << 24) |
               ((r & 0xFF) << 16) |
               ((g & 0xFF) << 8)  |
               ((b & 0xFF) << 0);
      }
    }
    return 0xFF0078D4;
  }
}

DLLIMPORT BOOL implGetThemeIsDark() {
    HKEY hk;
    LONG lReturn = RegOpenKeyEx(HKEY_CURRENT_USER, "Software\\Microsoft\\Windows\\CurrentVersion\\Themes\\Personalize", 0, KEY_READ, &hk);
    if (ERROR_SUCCESS == lReturn) {
        DWORD dwValue;
        DWORD dwSize = sizeof(dwValue);
        lReturn = RegQueryValueEx(hk, "AppsUseLightTheme", NULL, NULL, (LPBYTE)&dwValue, &dwSize);
        RegCloseKey(hk);
        if (ERROR_SUCCESS == lReturn) return dwValue == 0;
    }
    return TRUE;
}

DLLIMPORT BOOL implGetTransparencyEnabled() {
    HKEY hk;
    LONG lReturn = RegOpenKeyEx(HKEY_CURRENT_USER, "Software\\Microsoft\\Windows\\CurrentVersion\\Themes\\Personalize", 0, KEY_READ, &hk);
    if (ERROR_SUCCESS == lReturn) {
        DWORD dwValue;
        DWORD dwSize = sizeof(dwValue);
        lReturn = RegQueryValueEx(hk, "EnableTransparency", NULL, NULL, (LPBYTE)&dwValue, &dwSize);
        RegCloseKey(hk);
        if (ERROR_SUCCESS == lReturn) return dwValue == 1;
    }
    return TRUE;
}

DLLIMPORT BOOL implApplyBlur(HWND hwnd, DWORD blurType, BOOL isDark) {
  OSVERSIONINFOEX osver;
  osver.dwOSVersionInfoSize = sizeof(OSVERSIONINFOEX);
  if (!GetVersionEx((LPOSVERSIONINFOA)&osver)) {
    return FALSE;
  }
  DWORD build = osver.dwBuildNumber;
  DWORD baseVer = osver.dwMajorVersion;
  if (baseVer < 10) {
    WindowBlurBehindFast(hwnd, TRUE);
  }
  else {
    // Win 11
    if (build >= 22621) {
      WindowBackdropFast(hwnd, 0);
      WindowBackdropFast(hwnd, blurType == 1 ? 2 : (blurType == 0 ? 3 : blurType));
	  WindowImmersiveDarkModeFast(hwnd, isDark);
	  WindowMicaFast(hwnd, TRUE);
	  WindowBlurBehindFast(hwnd, TRUE);
	  WindowExtendFrameIntoClientAreaFast(hwnd);
    }
    else {
      DWORD dwBlurType = blurType <= 2 ? blurType : 2;
      WindowBlurBehindFast(hwnd, TRUE);
	  WindowExtendFrameIntoClientAreaFast(hwnd);
      if (isDark) {
        WindowImmersiveDarkModeFast(hwnd, TRUE);
	    WindowBlurFast(hwnd, dwBlurType, 0x00535353);
      }
      else {
        WindowImmersiveDarkModeFast(hwnd, FALSE);
	    WindowBlurFast(hwnd, dwBlurType, 0x00d3d3d3);
      }
    }
  }
  return TRUE;
}

DLLIMPORT BOOL implSetWindowRadius(HWND hwnd, DWORD radius) {
	RECT m_rc;
	GetWindowRect(hwnd, &m_rc);
    /*HRGN m_rgn = CreateRoundRectRgn(m_rc.left, m_rc.top, m_rc.right, m_rc.bottom, radius, radius);
    SetWindowRgn(hwnd, m_rgn, TRUE);*/

    HRGN m_rgn = CreateRoundRectRgn(0, 0, m_rc.right - m_rc.left, m_rc.bottom - m_rc.top, radius, radius);
    SetWindowRgn(hwnd, m_rgn, TRUE);
}

DLLIMPORT BOOL implIsWindows11() {
    OSVERSIONINFOEX osver;
    osver.dwOSVersionInfoSize = sizeof(OSVERSIONINFOEX);
    if (!GetVersionEx((LPOSVERSIONINFOA)&osver)) {
      return FALSE;
    }
    DWORD build = osver.dwBuildNumber;
    return build >= 22621;
}

DLLIMPORT void implSetWindowIsDark(HWND hwnd, BOOL isDark) {
    WindowImmersiveDarkModeFast(hwnd, isDark);
}

// 0 : Disable
// 1 : Auto
// 2 : Arcylic
// 3 : Mica
// 4 : Mica Alt & Tab
void WindowBackdropFast(HWND hwnd, DWORD type) {
	DWM_SYSTEMBACKDROP_TYPE i;
	switch (type) {
		default:
		case 0:
			i = DWMSBT_NONE;
			break;
		case 1:
			i = DWMSBT_AUTO;
			break;
		case 2:
			i = DWMSBT_TRANSIENTWINDOW;
			break;
		case 3:
			i = DWMSBT_MAINWINDOW;
			break;
		case 4:
			i = DWMSBT_TABBEDWINDOW;
			break;
	}

	DwmSetWindowAttribute(hwnd, DWMWA_SYSTEMBACKDROP_TYPE, &i, sizeof(i));
	DwmFlush();
}

void WindowBlurBehindFast(HWND hwnd, BOOL enabled) {
	DWM_BLURBEHIND data = {1};
	data.dwFlags = DWM_BB_ENABLE;
	data.fEnable = enabled;
	data.hRgnBlur = NULL;
	DwmEnableBlurBehindWindow(hwnd, &data);
	DwmFlush();
}

void WindowExtendFrameIntoClientAreaFast(HWND hwnd) {
	MARGINS m = {-1};
	DwmExtendFrameIntoClientArea(hwnd, &m);
	DwmFlush();
}

void WindowImmersiveDarkModeFast(HWND hwnd, BOOL enabled) {
	int val = enabled ? 1 : 0;
	DwmSetWindowAttribute(hwnd, DWMWA_IMMERSIVE_DARK_MODE, &val, sizeof(val));
	DwmFlush();
}

void WindowMicaFast(HWND hwnd, BOOL enabled) {
	int val = enabled ? 1 : 0;
	DwmSetWindowAttribute(hwnd, DWMWA_MICA, &val, sizeof(val));
	DwmFlush();
}

// 0 : Disable
// 1 : Blur
// 2 : Arcylic
void WindowBlurFast(HWND hwnd, DWORD type, int color) {
HMODULE hUser = GetModuleHandle(TEXT("user32.dll"));
    if (hUser)
    {
	    pfnSetWindowCompositionAttribute setWindowCompositionAttribute = (pfnSetWindowCompositionAttribute)GetProcAddress(hUser, "SetWindowCompositionAttribute");
	    if (setWindowCompositionAttribute) 
	    {
			ACCENT_STATE st;

			switch (type) {
				default:
				case 0:
					st = ACCENT_DISABLED;
					break;
				case 1:
					st = ACCENT_ENABLE_BLURBEHIND;
					break;
				case 2:
					st = ACCENT_ENABLE_ACRYLICBLURBEHIND;
					break;
			}

		    ACCENT_POLICY accent = { st, 0, 0, 0 };

		    // accent.GradientColor = (152 << 24) | (0x2B2B2B & 0xFFFFFF);
		    // accent.GradientColor = 0x00444444;
		    accent.GradientColor = color;
		    accent.AccentFlags = 0x03;

		    WINDOWCOMPOSITIONATTRIBDATA data;
		    data.Attrib = WCA_ACCENT_POLICY;
		    data.pvData = &accent;
		    data.cbData = sizeof(accent);

		    setWindowCompositionAttribute(hwnd, &data);
	    }
    }
	DwmFlush();
}

BOOL APIENTRY DllMain (HINSTANCE hInst     /* Library instance handle. */ ,
                       DWORD reason        /* Reason this function is being called. */ ,
                       LPVOID reserved     /* Not used. */ ) {
    switch (reason) {
      case DLL_PROCESS_ATTACH:
        break;

      case DLL_PROCESS_DETACH:
        break;

      case DLL_THREAD_ATTACH:
        break;

      case DLL_THREAD_DETACH:
        break;
    }

    /* Returns TRUE on success, FALSE on failure */
    return TRUE;
}
