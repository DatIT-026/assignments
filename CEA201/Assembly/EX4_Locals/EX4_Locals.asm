.486
.model flat, stdcall
option casemap :none

include \masm32\include\windows.inc
include \masm32\macros\macros.asm

include \masm32\include\masm32.inc
include \masm32\include\gdi32.inc
include \masm32\include\user32.inc
include \masm32\include\kernel32.inc

includelib \masm32\lib\masm32.lib
includelib \masm32\lib\gdi32.lib
includelib \masm32\lib\user32.lib
includelib \masm32\lib\kernel32.lib

show_text PROTO :DWORD                      ; prototype a method + type of parameter

.code
start:
    call main
    exit

main proc
    LOCAL txtinput:DWORD                   ; a "handle" for the text returned by "input"
    mov txtinput, input("Type some text at the cursor : ")          ; get input string
    invoke show_text, txtinput
    ret
main endp

show_text proc string:DWORD
    print chr$("This is what you typed at the cursor",13,10, "         *** ")
    print string
    print chr$(" ***",13,10)
    ret
show_text endp

end start