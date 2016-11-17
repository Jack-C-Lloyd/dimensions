# dimensions

Developers often need to model units of measure, because objects in the real
world are subject to these measures. We could use plain numeric types but how 
then would we know whether this method expects grams, kilograms or something 
else, and what type of money value it returns?

    int costOfApples(int weight)

The need for *dimension types* is explained by A.J. Kennedy as follows [1] 

"Dimensions are to science what types are to programming. In science and engineering, 
dimensional consistency provides a first check on the correctness of
an equation or formula, just as in programming the typability of a program or
program fragment eliminates one possible reason for program failure.
What then of dimensions in programming? After all, the writing of a scientific
program involves turning a set of equations which model some physical situation
into a set of instructions making up a computer program. It still makes sense
to talk about the dimensions of a formula, even when represented as a program
fragment written in some programming language."

A specification for dimension types in Java has been accepted [2], so these will
be available in a future version of the language. We can use them already, using 
the "reference implementation" from JScience.org, as well as the `javax.measure` API.

Clone the repository with the following command:

    $ git clone https://github.com/jimburton/dimensions.git

This tiny project uses maven to download the `unitsofmeasurement` API. So if you're using Eclipse,
import the repository as a maven project. IntelliJ can open maven projects without any bother.
Maven downloads the JScience library for you but you want the sources as well, so 
that you can read the docs. In Eclipse, you can tell maven to do this in 
*Window / Preferences / Maven*. In ItelliJ, open the Maven Projects tool window,
find the button that lets you execute a maven goal and execute these goals:

    mvn dependency:sources
    mvn dependency:resolve -Dclassifier=javadoc
    
Two of the important classes you need to understand are `Amount` and `Unit`. Read the docs
for them in your IDE of [here](http://jscience.org/api/org/jscience/physics/amount/package-summary.html) 
and [here](http://jscience.org/api/javax/measure/unit/Unit.html). 

## Do you know the way to San Jose?

The `Main` class contains an example of using the `unitsofmeasurement` API. It
calculates the fuel cost of a 400 mile journey for a US tourist in Europe. Use
the same ideas to calculate the cost of a journey from New York to San Jose
(google the distance by road) and convert it to Pounds Sterling for your British 
users.

## It's not Rocket Science

You'll have heard of the Mars Climate Orbiter disaster [3], which was caused by a 
dimension conversion error. Programmers at NASA treated a SI (or metric) 
value as an Imperial measure (miles, tons and so on, still used in the US). The
data that caused the error was the measure of *impulse*, which is a measure of 
`force . time`. Rather than calculating the impulse of the whole spaceship, it's more
useful to know the *specific impulse*, which is the impulse per unit of fuel. 
The Imperial measure of force is `lbf` ("pounds of force") so NASA was measuring specific 
impulse as `lbf.s/lb` ("(pounds of force times seconds) divided by pounds"). 
The SI unit of force is the `Newton`, (`N`) so the European programmers were expecting 
specific impulse to be in units of `N.s/kg`.


Start by creating an `Amount` with the right dimensions to represent the Imperial value.
There are two ways to do this. The library can parse strings that represent values,
so you could do this:

    Amount<?> orbiterImpulse = Amount.valueOf("310 lbf*s/lb"); 
    
There is a more flexible way though, using the version of `valueOf` that takes a numeric 
value (either a `long` or a `double`) and the `Unit` it is measured in. Non-SI (e.g. Imperial)
units are defined here, while SI units are defined here. The `Unit` class comes with 
methods to make compound units. For instance, accelleration is measured in `m / s^2`, which 
we can create a `Unit` for: 

    Unit<?> acc = METRE.divide(SECOND.pow(2));
    
Then we can make a `Value` representing acceleration at gravitational acceleration on Earth:
    
    Amount<?> g = Amount.valueOf(9.78, acc); 

The impulse of the orbiter as it approached Mars was apparently about `310 lbf.s/ton`.
Use the `unitsofmeasurement` API to convert this value into `N.s/kg`, saving $327 
million and a lot of red faces.

[1] https://www.cl.cam.ac.uk/techreports/UCAM-CL-TR-391.pdf

[2] JSR 363: https://github.com/unitsofmeasurement/unit-api

[3] https://en.wikipedia.org/wiki/Mars_Climate_Orbiter
