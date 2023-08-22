Write-Output "Compiling UiShell.o..."
gcc.exe -c dllmain.c -o dllmain.o -DBUILDING_DLL=1 --verbose
Write-Output "Wrapping UiShell.dll..."
dllwrap.exe --output-def libUiShell.def --implib libUiShell.a dllmain.o -static-libstdc++ -static-libgcc dwmapi.dll --no-export-all-symbols --add-stdcall-alias -o UiShell.dll