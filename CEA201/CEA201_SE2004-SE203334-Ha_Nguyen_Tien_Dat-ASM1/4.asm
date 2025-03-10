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
     LOCAL a:DWORD
     LOCAL b:DWORD
     LOCAL x:DWORD
     
     mov eax, input("Enter the coefficient a: ")
     mov a, sval(eax)
     
     mov eax, input("Enter the coefficient b: ")
     mov b, sval(eax)

     ; Check for a = 0 to avoid division by zero
     cmp a, 0
     je error

     ; Compute x = -b/a
     mov eax, b
     neg eax                        ; now eax = -b
     cdq                            
     idiv a                         ; eax = -b/a
     mov x, eax
 
     ; Display the result
     print chr$("The solution x is: ");
     print str$(x);
     print chr$(13,10);

     ret

error:
     print chr$("Error: Coefficient a cannot be zero!");
     print chr$(13,10);
 
     ret
    
main endp

end start