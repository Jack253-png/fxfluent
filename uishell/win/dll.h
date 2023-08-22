#ifndef _DLL_H_
#define _DLL_H_

#if BUILDING_DLL
# define DLLIMPORT __declspec (dllexport)
#else /* Not BUILDING_DLL */
# define DLLIMPORT __declspec (dllimport)
#endif /* Not BUILDING_DLL */
#include <windows.h>

DLLIMPORT void HelloWorld(void);
DLLIMPORT DWORD GetCompositionColor(void);
DLLIMPORT BOOL GetThemeIsDark(void);

#endif /* _DLL_H_ */
