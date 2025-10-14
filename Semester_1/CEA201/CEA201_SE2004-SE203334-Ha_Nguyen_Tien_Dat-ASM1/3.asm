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
     LOCAL var3:DWORD
     LOCAL sum:DWORD
     LOCAL avg:DWORD
     
     mov eax, input("Enter num1: ")
     mov var1, sval(eax)
     
     mov eax, input("Enter num2: ")
     mov var2, sval(eax)
     
     mov eax, input("Enter num3: ")
     mov var3, sval(eax)


     ; Calculate Sum
     mov eax, var1
     add eax, var2
     add eax, var3
     mov sum, eax

     ; Calculate Average
     mov eax, sum
     cdq                            ; Extend sign of EAX into EDX
     mov ebx, 3                     ; Divisor 3
     idiv ebx                       ; Divide EDX:EAX by EBX
     mov avg, eax

     ; EAX is a box where the computer keeps numbers while it does math
     ; EDX is another box that helps when numbers get too big
     ; EBX is just another helper box for holding numbers
     ; cdq makes the number bigger so we can divide correctly
     ; idiv splits one numbre by another, like sharing cookies equally
 
     ; Display the result
     print chr$("The sum is: ");
     print str$(sum);
     print chr$(13,10);

     print chr$("The average is: ");
     print str$(avg);
     print chr$(13,10);    
     
     ret
    
main endp

end start