# dimensions

Developers often need to model units of measure, because objects in the real
world are subject to these measures. We could use plain numeric types but how 
then would we know whether this method expects grams, kilograms or something 
else, and what type of money value it returns?

    int costOfApples(int weight)

The need for *dimension types* is explained by A.J. Kennedy as follows [1] 

"Dimensions are to science what types are to programming. In science and en-
gineering, dimensional consistency provides a first check on the correctness of
an equation or formula, just as in programming the typability of a program or
program fragment eliminates one possible reason for program failure.
What then of dimensions in programming? After all, the writing of a scientific
program involves turning a set of equations which model some physical situation
into a set of instructions making up a computer program. It still makes sense
to talk about the dimensions of a formula, even when represented as a program
fragment written in some programming language."

A specification for dimension types in Java has been accepted [2], so these will
be available in a future version of the language. We can use them already, using 
the "reference implementation" from JScience.org, as this lab exercise does.

Clone the repository with the following command:

    $ git clone https://github.com/jimburton/dimensions.git

It uses maven to download the `unitsofmeasurement` API. So if you're using Eclipse,
import it as a maven project. 

## Do you know the way to San Jose?

The `Main` class contains an example of using the `unitsofmeasurement` API. It
calculates the fuel cost of a 400 mile journey for a US tourist in Europe. Use
the same ideas to calculate the cost of a journey from New York to San Jose
(google the distance) and convert it to Pounds Sterling for your British users.

## It's not Rocket Science

You'll have heard of the Mars Climate Orbiter disaster [3], which was caused by a 
dimension conversion error dimension. Programmers at NASA treated a SI (or metric) 
value as an Imperial measure (miles, tons and so on, still used in the US). The
data that caused the error was the measure of *impulse*, which is a measure of 
`force * time / weight`. The Imperial measure of force is `lbf` ("pounds of force")
so NASA was measuring impulse as `lbf*s/ton`. The SI unit of force is the `Newton`,
(`N`) so the European programmers were expecting impulse to be in units of `N*s/kg`.

The impule of the orbiter as it approached Mars was apparently about 310 lbf*s/ton.
Use the `unitsofmeasurement` API to convert this value into `N*s/ton`, saving $327 
million and a lot of red faces.



[1] https://www.cl.cam.ac.uk/techreports/UCAM-CL-TR-391.pdf
[2] JSR 363: https://github.com/unitsofmeasurement/unit-api
[3] https://en.wikipedia.org/wiki/Mars_Climate_Orbiter
