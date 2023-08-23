/* Replace "dll.h" with the name of your header */
#include "dll.h"
#include "WindowCompositionAttribute.h"
#include <dwmapi.h>
#include <windows.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdio.h>

DLLIMPORT void HelloWorld () {
    MessageBox (0, "Hello World from DLL!\n", "Hi", MB_ICONINFORMATION);
}

DLLIMPORT DWORD GetCompositionColor() {
  DWORD color = 0;
  BOOL opaque = FALSE;
  
  HRESULT hr = DwmGetColorizationColor(&color, &opaque);
  if (SUCCEEDED(hr)) return color;
  else return 13924352;
}

DLLIMPORT BOOL GetThemeIsDark() {
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

DLLIMPORT BOOL GetTransparencyEnabled() {
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
