# TicTacToe <br />
<small>CSC242: Artificial Intelligence</small><br />
<small>Prof. George Ferguson</small><br />
<small>University of Rochester</small>

<small>**Author:** John S. Bissonette <jbisson2@u.rochester.edu></small> <br />
<small>**NetID:** jbisson2</small> <br />
<small>**Date:** 26 September, 2017</small> <br />
<small>**Assignment:** Project 1</small>

**Academic Honesty Affirmation:**<br />
> I affirm that I have not given or receive any unauthorized help on this assignment,
> and that this work is my own.

## Overview

This project asked us to create an AI opponent playing the game of Tic Tac Toe. The rubric breaks down
the assignment into two parts: the first part is to implement the AI for a classic game of Tic Tac Toe,
and the second part is to implement the AI playing a game of Ultimate Tic Tac Toe, also known as 9-Board
Tic Tac Toe. I decided to code the program in Java. The algorithm we were directed to use for the AI is
the [Minimax algorithm][1], using a heuristic evaluation function and alpha-beta pruning. While it was noted
in the project rubric that this is overkill for a simple game of Tic Tac Toe, it was absolutely necessary to use
this algorithm when playing 9-Board Tic Tac Toe. 

The project contains the following directory structure:

- **`TicTacToe/`** - the project root folder
    - **`out/`** - the compiled Java bytecode from source
    - **`src/`** - source code
    
## Classic TicTacToe

For my classic TicTacToe application, I created several classes: Board, Game, State, and MinimaxAB. The Board class 
contains all the logic for manipulating a game board, and for performing a deep clone of the board for use in the 
MinimaxAB class.

### Minimax Implementation

My implementation of the Minimax algorithm includes the ability to set a search depth for the tree, a heuristic 
evaluation function for determining the utility of a particular terminal state, and includes an alpha-beta cutoff for
 both the `getMax()` and `getMin()` functions.

## Things to Note:

Within the Board class, I implemented the instance variable **movesAvailable** as a `HashSet<Integer>`
because I ran into a problem with its prior implementation as an `ArrayList<Integer>`. The problem was
when removing an element from the ArrayList, Java reindexed the elements in the list. This broke the
program:

```java
// ArrayList initialized with 9 zeroes
movesAvailable = new ArrayList<>(Collections.nCopies(9, 0));

movesAvailable.remove(3);

for (int i = 0; i < movesAvailable.size(); i++) {
  System.out.print(String.format("%d, ", movesAvailable.get(i)));
}

// outputs
0, 1, 2, 3, 4, 5, 6, 7,

// expected to see
0, 1, 2, 4, 5, 6, 7, 8,

```

This behavior was unexpected, and it wasn't until I looked up the Java API docs on ArrayList#remove
that I found out that it shifts the index position of all the elements to the left. Therefore, I went
with the `HashSet<Integer>` as it allows the null value and doesn't reindex its elements because it is
backed by a HashTable.







[1]: https://en.wikipedia.org/wiki/Minimax
