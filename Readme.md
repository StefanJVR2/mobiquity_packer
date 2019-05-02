# Mobiquity Packer Solution
## Problem (0 -1 Knapsack)
>"You want to send your friend a package with different things.
Each thing you put inside the package has such parameters as index number, weight and cost. The
package has a weight limit. Your goal is to determine which things to put into the package so that the
total weight is less than or equal to the package limit and the total cost is as large as possible.
You would prefer to send a package which weights less in case there is more than one package with the
same price."

## Solution
Dynamic programming is used to solve optimization problems such as the Knapsack problem
Using dynamic programming the problem set is used to build a table of values.
>The general idea:
>Compute the solutions to the subsub-problems
once and store the solutions in a table, so that they
can be reused (repeatedly) later.

This represents the data spatially, basically, to make the program run more efficiently, we pay in memory space.

## The Structure

The implementation uses the [chain-of-responsibility](https://sourcemaking.com/design_patterns/chain_of_responsibility) pattern. This pattern was used to separate the concerns clearly. These can then be individually unit tested, and individually used by the consumer of the artifact. The pattern also future proofs the application by allowing for new Handlers with the new functionality to be introduced.

The request is built up during handler execution

![](https://sourcemaking.com/files/v2/content/patterns/Chain_of_responsibility_1.png)
