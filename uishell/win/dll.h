#ifndef _DLL_H_
#define _DLL_H_

#if BUILDING_DLL
# define DLLIMPORT __declspec (dllexport)
#else /* Not BUILDING_DLL */
# define DLLIMPORT __declspec (dllimport)
#endif /* Not BUILDING_DLL */
#include <windows.h>

DLLIMPORT void implNativeWarning(void);
DLLIMPORT DWORD implGetCompositionColor(void);
DLLIMPORT BOOL implGetThemeIsDark(void);
DLLIMPORT BOOL implGetTransparencyEnabled(void);
DLLIMPORT BOOL implApplyBlur(HWND, DWORD, BOOL);
DLLIMPORT BOOL implSetWindowRadius(HWND, DWORD);
DLLIMPORT BOOL implIsWindows11(void);
DLLIMPORT void implSetWindowIsDark(HWND, BOOL);
DLLIMPORT void implHideBar(HWND);

#endif /* _DLL_H_ */
