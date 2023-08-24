#ifndef _DLL_H_
#define _DLL_H_

#if BUILDING_DLL
# define DLLIMPORT __declspec (dllexport)
#else /* Not BUILDING_DLL */
# define DLLIMPORT __declspec (dllimport)
#endif /* Not BUILDING_DLL */
#include <windows.h>

DLLIMPORT void NativeWarningReflect(void);
DLLIMPORT DWORD GetCompositionColor(void);
DLLIMPORT BOOL GetThemeIsDark(void);
DLLIMPORT BOOL GetTransparencyEnabled(void);
DLLIMPORT BOOL ApplyBlur(HWND, DWORD, BOOL);
DLLIMPORT BOOL SetWindowRadius(HWND, DWORD);
DLLIMPORT BOOL IsWindows11(void);

#endif /* _DLL_H_ */
