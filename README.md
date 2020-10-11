# STONXTRADER

## About

A slightly adventurous stock trading game written for a programming
assignment for a Java programming university course.

You assume a role of a slightly naive 20-year-old newbie stock 
trader that wants to get as rich as possible as quickly as possible, so they 
can retire as early as possible. There is no winning or losing per se, only
highscores. Each day is represented by a turn, and a year is 
360 days or 2880 turns. If you stonks so much the
64-bit balance variable overflows, consider it a win as well.
Starting amount of money is a small loan of a million dollars with 5 % annual
interest rate and lifetime savings of $100k. There are no cents, it's a nuisance.

It's a somewhat scaled down earlier failed Java semester work
that was supposed to be a sandbox RPG engine, while I had only 3 months of
experience with Java, so I instead focused on perfecting the [Juliascii](https://github.com/637man/juliascii)
fractal viewer, which was another semester work. I owe this level
of Stonxtrader completion to SARS-CoV-2, as I had more time during the
spring lockdown, and it happened not to leave my PC even for presentation.
However, that means the code is "works on my machine" quality.

In 1 of the following courses we are supposed to transform this into a MUD
and/or give it a GUI. MUDifiying this could be interesting.


## Build and run instructions

The game is written in NetBeans in Java 11. Since IDEs are bloat, given this
is just a text game, the project hierarchy has been flattened. That means the
game data is no longer in the default `./st`, but in `.`. This needs to be
explicitly specified.

    javac *.java
    java Main -g .

 ### Arguments
 
 * -g \<folder\> -- Game data folder, should contain \_player.txt and \_places.csv
 * -s \<number\> -- Random seed, useful for speedruns and testing
 * -i \<filename\> -- Input file with commands
 * -o \<filename\> -- Output file for later inspection
 
 Options -i and -o are bloat on POSIX-compliant systems, they are left there mainly for Windows users.


## Places

There are some real as well as fictional stock markets trading resemblants
of real or fictional companies respectively. Also included are global 
(crypto)currency and commodities market. Traveling takes 1 turn.
You travel by typing "go" followed by either the acronym or the place
according to the following table:

Name                                  | Acronym | Place
------------------------------------- | ------- | --------------------------------
New York Stock Exchange "Wall Street" | NYSE    | newyork wallstreet nyc
Tokyo Stock Exchane                   | TSE     | tokio tokyo japan
Shanghai Stock Exchange               | SSE     | shanghai china
Euronext                              | EU      | euronext euro europe
Frankfurter Wertpapierbörse           | DB      | frankfurt germany fwb
Prague Stock Exchange                 | PSE     | prague praha czechia
Bratislava Stock Exchange             | BSSE    | bratislava pressburg slovakia
Liberland Stock Exchange              | LLSE    | liberpolis liberland
Agricultural Commodity Exchange       |         | agriculture food agro groceries
(Bio)fuel Commodity Exchange          |         | fuel biofuel oil petro
Refined Elements & Alloys Commodity Exchange |  | refined steel diamond alloys
Synthetic Materials Commodity Exchange |        | synthetic plastic nano rubber
Foreign Currency Exchange             |         | forex currencies money
Elements Exchange                     |         | elements periodic raw pure

CONTENT WARNING: Not all prices have been researched thoroughly.


## Commands

Additional emphasis has been made on user experience, the commands are smart
and try to be helpful instead of complaining when the user makes a mistake.
The commands are (NYI = Not Yet Implemented):

Syntax                     | Notes
-------------------------- | --------------------------------------------------
go \<place\>               | implies list *, if place == "" then list places
buy \<item\> \<quantity\>  | default quantity 1
sell \<item\> \<quantity\> | ditto
borrow \<amount\>          |
payback \<amount\>         | if amount == "" then payback as much as possible
list                       | "" portfolio, "*" on exchange
graph \<item\>             | ditto (NYI)
sleep \<amount\>           | default amount 1
stats                      |
quit                       |
retire                     |
help                       |
scores \<order\>           | order: fund, traded, retire, lived (NYI)


## Game data formats

All lines starting with # are always ignored. Despite the extenstion
and official specification, semicolons are used as a separator.

### Initial player stats (_player.txt)

    # InitialMoney
    100000
    # InitialLoan
    1000000
    # StartingPlace
    prague
    # InterestRate
    1.05
    # Inflation
    1.02

### Place list (_places.csv):

    # PrimaryName;LongName;Alias1;Alias2;Alias3
    bratislava;Bratislava Stock Exchange;pressburg;slovakia;bsse

### Place description (PrimaryName.csv as in places.csv):

    # SecurityName;Volatility;InitialPrice;Supply
    Slovnaft, a. s.;5;100;1000

Game save consists of \_player.txt and all PrimaryName.csv. Saving and loading is NYI, though.

### High scores table format (_scores.csv)

    # LivedUntil = TradedFor + RetiredFor + 20
    # Name;RetirementFund;TradedFor;RetiredFor;LivedUntil
    637man;20000000;40;20;80
    getj00;5000000000;50;40;110

Displaying high scores is NYI, but the game still writes to it.
