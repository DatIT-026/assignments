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
    LOCAL var1:DWORD                          ; First input number
    LOCAL var2:DWORD                          ; Second input number
    LOCAL sum:DWORD                           ; Sum of the 2 numbers
    
    mov eax, input("Enter number 1 : ") 
    mov var1, sval(eax)                       ; convert string to integer & store in var1

    mov eax, input("Enter number 2 : ")
    mov var2, sval(eax)                       ; convert string to integer & store in var2

    mov eax, var1                             ; Move var1 into EAX
    add eax, var2                             ; Add var2 to EAX (EAX now holds the sum)
    mov sum, eax                              ; Store the sum in the 'sum' variable

    ; Display thhe result
    print chr$("Sum = ")
    print str$(sum)                           ; print the sum as a string
    print chr$(13,10)                         ; Newline

    ret                                       ; Return from main procedure
main endp
end start