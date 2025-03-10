.486
.model flat, stdcall
option casemap :none

include \masm32\include\windows.inc
include \masm32\macros\macros.asm
include \masm32\include\masm32.inc
include \masm32\include\gdi32.inc
include \masm32\include\user32.inc
include \masm32\include\kernel32.inc
include \masm32\include\masm32rt.inc

includelib \masm32\lib\masm32.lib
includelib \masm32\lib\gdi32.lib
includelib \masm32\lib\user32.lib
includelib \masm32\lib\kernel32.lib

.code

start:
    call main
    exit

main proc
    LOCAL var1:DWORD
    LOCAL var2:DWORD
    LOCAL sum:DWORD
    LOCAL i:DWORD
    LOCAL n:DWORD

    mov eax, input("n = ")
    mov n, sval(eax)
    mov i, 0

    print chr$("Time",13,10)

loop_calc:
    mov eax, i
    cmp eax, n
    jge end_loop

    ; Print the current count (starting from 1)
    mov eax, i
    add eax, 1
    print str$(eax)
    print chr$(13,10)

    mov eax, input("Enter number 1: ")
    mov var1, sval(eax)

    mov eax, input("Enter number 2: ")
    mov var2, sval(eax)

    mov eax, var1
    add eax, var2
    mov sum, eax

    print chr$("The sum is: ")
    print str$(sum)
    print chr$(13,10)

    inc i
    jmp loop_calc

end_loop:
    ret

main endp

end start
