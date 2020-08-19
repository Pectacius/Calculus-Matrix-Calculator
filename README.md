# FunctionCalculator

Calculator that performs operations on functions

## Description
The application consists the basic functions of a hand-held calculator such as performing addition, 
subtraction, multiplication, division, exponentiation, logarithms, trigonometry on numeric values.
The major difference is that *the user can input functions and perform new operations such as*:

 - differentiating
 - integrating
 - evaluating the function at a specific value
 - finding zeros
 - graphing
 - retrieving previous inputs.

Math is a very versatile tool in many fields such as physics as it can be used to represent 
the relationship between different quantities such as flux and electrical fields. In some situations, 
the manipulation of functions becomes tedious and the focus shifts away from the theory and concepts
and towards the mathematical manipulation. This application aims to help users shift their focus back to
the theories and concepts.


## Guide On Usage

- To run app, compile and run `Main.java`

### `Evaluate Expression` Button
- Click buttons to create a valid math expression, expression is displayed on screen, used `CE` to clear
everything and `ENTER` to evaluate the expression (*Note: parenthesis must in-close transcendental functions*)
- `NEXT` and `PREV` to navigate through saved memory (only usable when `Mode: Display Memory`)

#### `Menu` Options
- Open `Menu` by clicking on the menu drop-down from the top left
- `To Binary` converts current answer (rounded to nearest integer) to a binary value.
- `Save` saves current expression and answer to memory
- `Delete Memory` removes current viewed memory value
- `View Memory` changes current mode to `Mode: Display Memory`
- `Calculate` changes mode to `Mode: Calculate`
- `Back` returns back to main menu

### `Evaluate Function` Button
- Same with `Evaluate Expression` except functions are inputted (The "X" is the variable).
(Note that the implicit "*" in "3X" must be typed, same goes with the brackets with transcendental functions).

#### `Menu` Options
- `Evaluate f(x)` opens a popup. Input a number in the text-box and then press "okay". The current function will be
evaluated at that value.
- `f'(x)` differentiates the current function
- `Definate Integral` integrates the current function with a specified lower and upper-bound
- `Back` returns back to main menu

### `Load` Button
- Clicking opens a pop-up that prompts if previous saved to file values should be loaded

### `Quit` Button
- Exits application, prompts if saved values should be saved to file


## Acknowledgments:
- Background image taken from: https://cdn3.vectorstock.com/i/1000x1000/56/67/the-science-and-mathematics-abstract-background-vector-11335667.jpg
- LoadErrorIMG.jpg taken from: https://lh5.ggpht.com/_ZuHHjM8p4AQ/TZh1HeKHXmI/AAAAAAAABCc/RcTWvc7rDXY/github_404_thumb%5B1%5D.png?imgmax=800
- Exan font by John Carlos, taken from: https://www.behance.net/gallery/36169711/Exan-3-Free-Font
- Typodermic font taken from: https://www.myfonts.com/fonts/typodermic/nasalization/regular/

