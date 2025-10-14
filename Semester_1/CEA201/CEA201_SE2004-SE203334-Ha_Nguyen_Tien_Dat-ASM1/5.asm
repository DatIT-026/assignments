.486
.model flat, stdcall
option casemap :none
include \masm32\include\windows.inc
include \masm32\macros\macros.asm
include \masm32\include\masm32.inc
include \masm32\include\masm32rt.inc
includelib \masm32\lib\masm32.lib

.code
start:
    call main
    exit

main proc
    LOCAL v1:DWORD
    LOCAL v2:DWORD
    LOCAL result:DWORD

    ; Input v1
    mov eax, input("Enter the first number (v1): ")
    mov v1, sval(eax)

    ; Input v2
    mov eax, input("Enter the second number (v2): ")
    mov v2, sval(eax)

    ; v1 + v2
    mov eax, v1
    add eax, v2
    mov result, eax
    
    print chr$("v1 + v2 = ");
    print str$(result);
    print chr$(13,10);

    ; v1 - v2
    mov eax, v1
    sub eax, v2
    mov result, eax
    
    print chr$("v1 - v2 = ");
    print str$(result);
    print chr$(13,10);

    ; v1 * v2
    mov eax, v1
    imul v2
    mov result, eax
    
    print chr$("v1 * v2 = ");
    print str$(result);
    print chr$(13,10);

    ; v1 / v2 (with zero-check)
    cmp v2, 0
    je div_error
    mov eax, v1
    cdq
    idiv v2
    mov result, eax
    
    print chr$("v1 / v2 = ");
    print str$(result);
    print chr$(13,10);
    
    jmp end_div

div_error:
    print chr$("Error: Division by zero!");
    print chr$(13,10);

end_div:
    ret
main endp
end start
