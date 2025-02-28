.486
.model flat, stdcall
option casemap :none

; Include necessary libraries and headers
include \masm32\include\windows.inc
include \masm32\macros\macros.asm
include \masm32\include\masm32.inc
include \masm32\include\gdi32.inc
include \masm32\include\user32.inc
include \masm32\include\kernel32.inc
include \masm32\include\masm32rt.inc

; Link required libraries
includelib \masm32\lib\masm32.lib
includelib \masm32\lib\gdi32.lib
includelib \masm32\lib\user32.lib
includelib \masm32\lib\kernel32.lib

.code
start:
    call main          ; Call main procedure
    exit               ; Exit program

main proc
    LOCAL var1:DWORD ; First input number
    LOCAL var2:DWORD ; Second input number

    ; Input first number:
    mov eax, input("Enter number 1 : ")
    mov var1, sval(eax)                             ; Convert string to integer

    ; Input second number:
    mov eax, input("Enter number 2 : ")
    mov var2, sval(eax)                             ; Convert string to integer

    ; Compare the two numbers:
    mov eax, var1
    cmp eax, var2                                  ; compare var1 with var2
    je equal                                        ; Jump if equal
    jg bigger                                       ; Jump if var1 > var2
    jl smaller                                      ; Jump if var1 < var2

bigger:
    print chr$("The number 1 entered is greater than number 2",13,10)
    jmp over

smaller:
    print chr$("The number 1 entered is smaller than number 2",13,10)
    jmp over

equal:
    print chr$("2 numbers entered are equal.",13,10)

over:
    ret
main endp

end start
