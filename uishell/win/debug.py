import ctypes
import os

dll = ctypes.windll.LoadLibrary(os.path.join(os.getcwd(), "UiShell.dll"))
dll.HelloWorld()
clr = dll.GetCompositionColor()
print(clr)