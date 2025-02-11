#set page(
  paper: "a4",
  number-align: center,
)

#set text(font: "Cambria",
          size: 10.5pt,
)

= Exercise 1
Firstly, we convert the integral part and the fractional parts separately (Because *37.26#sub[10]* is a real number).

== 1. To hexadecimal (base 16 real number)
=== 1.a. Integral part

Start with the source (37.26), *divide it by 16* and use the whole number part as the new source. 
The remainder is added to the left side and repeat this process until the result is 0.

#align(center, table(
  columns: 4,
  stroke: none,

  [*Iteration*], [#table.vline()], [*Source*], [#table.vline()], [*Quotient*], [#table.vline()], [*Remainder*],
  [#table.hline()], 
  [1], [37], [2], [5],
  [#table.hline()], 
  [2], [2], [0], [2],
))

Reading the remainders from bottom to top, we get the hexadecimal representation of the integer part: *25*.

=== 1.b. Fractional Part
We use the fractional part (0.26) as the starting point, *multiply it by 16* and then use the new fractional part of the result as the next starting point.

Add the whole integral part of the result to the right side.
Repeat until fraction is 0 or we have enough digits.
#align(center, table(
  columns: 5,

  [*Iteration*], [*Source*], [*Result*], [*Integral part of result*], [*Fractional part of result*],
  [1], [0.26], [4.16], [4], [0.16],
  [2], [0.16], [2.56], [2], [0.56],
  [3], [0.56], [8.96], [8], [0.96],
  [4], [0.96], [15.36], [15#sub[10] = F#sub[16]], [0.36],
  [5], [0.36], [5.76], [5], [0.76],
  [6], [0.76], [12.16], [12#sub[10] = C#sub[16]], [0.16 (repeat)],
))

The fractions start repeating after 6 steps, so we stop there. This makes the fractional part in hexadecimal *428F5C#sub[16]*.

#align(left)[
  #text(12pt)[*Final Result:*] 37.26#sub[10] is equal to #highlight[*25.428F5C#sub[16]*].
]
== 2. To Binary (base 2 real number)
=== 2.a. Integral part
To convert the source (37.26#sub[10]) to binary, we need to convert the integral part (37).
Firstly,* divide it by 2*:

#align(center, table(
  columns: 4,
  stroke: none,

  [*Iteration*], [#table.vline()], [*Source*], [*Quotient*], [*Remainder*], [#table.hline()],
  [1], [37], [18], [1],
  [#table.hline()],
  [2], [18], [9], [0],
  [#table.hline()],
  [3], [9], [4], [1],
  [#table.hline()],
  [4], [4], [2], [0],
  [#table.hline()],
  [5], [2], [1], [0],
  [#table.hline()],
  [6], [1], [0], [1],
))

So, after reading the remainders from bottom to top, the integral part is *`100101`#sub[2]*.

=== 2.b. Fractional part
Similar to converting to hexadicimal, we multiply by 2 instead of 16.

#align(center, table(
  columns: 6,
  
  [*Iteration*], [*Source*], [*Result*], [*Integral part of result*], [*Fractional part of result*], [*Destination*],
  [1], [0.26], [0.52], [0], [0.52], align(right)[`0`],
  [2], [0.52], [1.04], [1], [0.04], align(right)[`01`],
  [3], [0.04], [0.08], [0], [0.08], align(right)[`010`],
  [4], [0.08], [0.16], [0], [0.16], align(right)[`0100`],
  [5], [0.16], [0.32], [0], [0.32], align(right)[`01000`],
  [6], [0.32], [0.64], [0], [0.64], align(right)[`010000`],
  [7], [0.64], [1.28], [1], [0.28], align(right)[`0100001`],
  [...], [...], [...], [...], [...], [...],
))
It keeps going endlessly, so we've stopped after 7 steps. The fractional part in binary is *`0100001`#sub[2]*.

#align(left)[
  #text(12pt)[*Final Result:*] 37.26#sub[10] is approximately #highlight()[*`100101.0100001`#sub[2]*].]

#pagebreak()

= Exercise 2
== The sign bit
Because 37.26#sub[10] is _positive_ #sym.arrow the sign is *0*.

== The exponent
37.26#sub[10] when transformed into binary is *`100101.0100001`#sub[2]* or we can representation it like this:

*`100101.0100001`#sub[2]* = *`1.001010100001`#sub[2]$#sym.times 2^5$*.

Therefore, the exponent is *5*. In _Excess_127_, the exponent is *5#sub[10]* + *127#sub[10]* = *132#sub[10]* = *`10000100`#sub[2]*.

== The mantissa
We have the normalized binary representation as *`1.001010100001`#sub[2]$#sym.times 2^5$* #sym.arrow the mantissa is *`001010100001`#sub[2]*.

The mantissa is currently 12 bits long, so we add 11 more bits to the right to make it 23 bits long.

This gives us the final mantissa: *`00101010000100000000000`#sub[2]*.

== Final Result
Ultimately, #highlight()[the _Excess_127_ representation of 37.26#sub[10] is *`01000010000101010000100000000000`#sub[2]*], which is the total of these components:

Sign: *0*;

Exponent: *`10000100`#sub[2]*;

Mantissa: *`00101010000100000000000`#sub[2]*.

#pagebreak()

= Exercise 3
Normalizing: *`101.1010000`#sub[2]* #sym.arrow *`1.011010000`#sub[2] $#sym.times 2^2$*.

The Shifter: *2*.

The Mantissa: *`011010000`#sub[2]*.

= Exercise 4
To perform the *32 - 25 = ?* using binary arithmetic and two's complement representation. There are several steps:

#align(left)[
  #text(11.5pt)[*Step 1*]: Convert the numbers to Binary]

- 32 in binary is *`100000`#sub[2]*.
- 25 in binary is *`011001`#sub[2]*.

#align(left)[
  #text(11.5pt)[*Step 2*]: Find the Two's Complement of 25]

To perform substaction, we need to find the 2's complement of 25.
- To do it, we add 1#sub[10] to the its one's complement (by invert the binary of 25).

#table(
  columns: 7,
  stroke: none,

  [*Orginal*], [#table.vline()], [0], [1], [1], [0], [0], [1],
  [*One's Complement*], [1], [0], [0], [1], [1], [0],
  [*Add 1#sub[10]*], [0], [0], [0], [0], [0], [1],
               [#table.hline()],
  [*Two's Complement*], [1], [0], [0], [1], [1], [1],
)

Now, the Two's complement of 25 is *`100111`#sub[2]*.

#align(left)[
  #text(11.5pt)[*Step 3*]: Add the Two's complement of 25 to 32, then convert the result back to Decimal (Base 10)]

Firstly, we add the two's complement of 25 to 32: 

#show table.cell.where(x: 0): strong

#table(
  columns: 2,
  gutter: 3pt,
  [*32#sub[10]*], align(right)[`100000`],
  [*25#sub[10] (2's complement)*], align(right)[`100111`],
  [*Sum*], [`1000111`],
)

Because of working with 6-bit numbers, we only keep the rightmost 6 bits and discard any carry beyond these bits:
- Result (rightmost 6 bits): *`000111`#sub[2]*.

Then, convert the binary result *`000111`#sub[2]* back to decimal:

#align(left)[
  #text(12pt)[
1 $#sym.times 2^2 + 1 #sym.times 2^1 + 1 #sym.times 2^0$ = 4 + 2 + 1 = 7.]
]

#align(left)[
  #text(12pt)[*Final Result:*] The binary substaction *32 - 25* using Arithmatic logic operation inside the CPU, with two's complement representation, #highlight()[the results is *7*] (binary result *`000111`#sub[2]*).  
]
#pagebreak();

= Exercise 5
Firstly, we write down the binary numbers in alignment:

#show table.cell.where(x: 0): strong
#table(
  columns: 1,
  gutter: 3pt,
  [`10001110`],
  [`10111001`],
)

Then, perform the XOR operation bit by bit and find the output:
- XOR (exclusive OR) outputs *`1`* when the bits are different, and *`0`* when the bits are the same.

#align(center, table(
  columns: 9,
  stroke: none,

  [*Input 1*], [#table.vline()], [1], [0], [0], [0], [1], [1], [1], [0],
  [*Input 2*], [1], [0], [1], [1], [1], [0], [0], [1], 
  [#table.hline()],
  [*Output*], [0], [0], [1], [1], [0], [1], [1], [1],
))

So, the result of *`10001110`#sub[2]* XOR *`10111001`#sub[2]* is #highlight()[*`00110111`#sub[2]*].