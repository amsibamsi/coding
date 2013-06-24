# schedule

## problem

`n` teams play against each other exactly once. all games are done one by one. no team is allowed to play twice in a row.

1. for which `n` is it possible to build a schedule?
1. build a valid schedule for `n` teams?

## input

number of teams.

## output

schedule of games that meets all conditions if if one exists, otherwise nil.

## solution

### existence of a solution

- there exists no solution for `n<5`
- for `n=5` a solution is: `[#{1 2} #{3 4} #{1 5} #{2 3} #{4 5} #{1 3} #{2 4} #{3 5} #{1 4} #{2 5}]`
- for `n>5` there is always a solution: 
    - if `n` is even:
        - partition game into rounds
        - each round each team plays one game
        - there will be `n-1` rounds with `n/2` games each
        - with `n>=6` each round will have at least 3 games
        - with 3 games or more it is always possible to schedule the first game in a round so that no team from the last game of the last round has to play again
        - it is always possible to partition the games into these rounds so that all teams have played against each other team exactly once
    - if `n` is odd:
        - every round one team has to skip
        - make each team skip exactly one round
        - there will be `n` rounds with `(n-1)/2` games each
        - from here on the same arguments can be applied as if `n` is even

### solve for a specific `n`

1. build a vector with all teams, e.g. `[1 2 3 4 5]`
1. find the two teams most left in the vector that have not yet played against each other
1. remove the two teams, schedule the game, add them again to the right
1. stop if total number of games is reached, else loop from step 2

- teams that have less games than others will always play first. this avoids a team has to catch up in the end and play in a row.
- teams that have played in the last game are put to the back and not scheduled again for the next game.
