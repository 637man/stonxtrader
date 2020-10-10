
 ~~~~~~~~~~~~~~~~~~~~~~~~~
   S T O N X T R A D E R
 ~~~~~~~~~~~~~~~~~~~~~~~~~

A slightly adventurous stock trading game by 637man written for a programming
assignment for 4IT101 - Programming in Java at the University of Economics in
Prague, assigned by David Kral and based on an text mode adventure game 
template provided by Michael Kolling, Lubos Pavlicek, and Jarmila Pavlickova.

In this game you assume a role of a slightly naive 20-year-old newbie stock 
trader that wants to get as rich as possible as quickly as possible, so they 
can retire as early as possible. The threshold for retiring and thus winning
this game is directly proportional to the time remaining until 100 years, 
accounting for inflation. Each day is represented by a turn, and a year is 
360 days, giving 2880 turns. If you somehow manage to stonks so much that the
64-bit integer holding the balance overflows, consider it a win as wll.
Starting amount of money is a small loan of a million dollars with 5 % annual
interest rate and lifetime savings of $100k. There are no cents in this game.


 Places
~~~~~~~~

There are some real as well as fictional stock markets trading resemblants
of real or fictional companies respectively. Also included are global 
(crypto)currency and commodities market. Traveling takes 1 turn.
You travel by typing "go" followed by either the acronym or the place
according to the following table:

Name                                   Acronym Place
-------------------------------------------------------------------------------
New York Stock Exchange "Wall Street"  NYSE    newyork wallstreet nyc
Tokyo Stock Exchane                    TSE     tokio tokyo japan
Shanghai Stock Exchange                SSE     shanghai china
Euronext                               EU      euronext euro europe
Frankfurter Wertpapierbörse            DB      frankfurt germany fwb
Prague Stock Exchange                  PSE     prague praha czechia
Bratislava Stock Exchange              BSSE    bratislava pressburg slovakia
Liberland Stock Exchange               LLSE    liberpolis liberland

Agricultural Commodity Exchange                agriculture food agro groceries
(Bio)fuel Commodity Exchange                   fuel biofuel oil petro
Refined Elements & Alloys Commodity Exchange   refined steel diamond alloys
Synthetic Materials Commodity Exchange         synthetic plastic nano rubber
Foreign Currency Exchange                      forex currencies money
Elements Exchange                              elements periodic raw pure

CONTENT WARNING: Not all prices have been researched thoroughly.


 Commands
~~~~~~~~~~

Additional emphasis has been made on user experience, the commands are smart
and try to be helpful instead of complaining when the user makes a mistake.
The commands are (NYI = Not Yet Implemented):

Syntax                  Notes
--------------------------------------------------------------------------
go <place>		implies list *, if place == "" then list places
buy <item> <quantity>	default quantity 1
sell <item> <quantity>	ditto
borrow <amount>
payback <amount>	if amount == "" then payback as much as possible
list			"" portfolio, "*" on exchange
graph <item> NYI	ditto
sleep <amount>		default amount 1
stats
quit
retire
help
scores <order> NYI	order: fund, traded, retire, lived


 Game data formats
~~~~~~~~~~~~~~~~~~~

All lines starting with # are always ignored. Despite the extenstion
and official specification, semicolons are used as a separator.

Initial player stats (_player.txt):
InitialMoney	100000
InitialLoan	1000000
StartingPlace	prague
InterestRate	1.05
Inflation	1.02

Place list (_places.csv):
#PrimaryName;LongName;Alias1;Alias2;Alias3
bratislava;Bratislava Stock Exchange;pressburg;slovakia;bsse

Place description (PrimaryName.csv as in places.csv):
#SecurityName;Volatility;InitialPrice;Supply
Slovnaft a. s.;5;100;1000

Game save consists of _player.txt and all PrimaryName.csv.

High scores table format (_scores.csv):
#LivedUntil = TradedFor + RetiredFor + 20
#Name;RetirementFund;TradedFor;RetiredFor;LivedUntil
637man;20000000;40;20;80
getj00;5000000000;50;40;110

