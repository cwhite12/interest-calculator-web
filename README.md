# Interest Calculator Task

## About
This project tests the functionality of the interest calculator app given, demonstrating approaches to testing, framework creation and coding skills.
## Tech Stack 
- Java 17+
- Junit 5
- Playwright (Java)
- Maven

## The framework includes: 
- Login to application
- Takes principal amount, interest amount and duration
- Displays results to two decimal places.

## TO DO with more time
- More boundary analysis 
- Validation, check all fields are actually mandatory and if they display error messages. 
- Negative test cases
- Edge test cases e.g state testing, test from a given state
- Add Data driven testing 
- Add reporting functionality / playwright tracing / screenshots etc.
- Add cross browser tests, webkit/ firefox / usability on mobile
- More logs
- More cleanup methods
- Performance tests
- Add config files / hide credentials

## Findings
- Remember me tick box doesn't actually remember anything, it also needs a space 'Remember me'
- When clicking login, the warning 'The UserName field is required' needs a space.
- Logo on interest calculator page doesn't load
- 'Requirements' button flashes
- Mandatory consent tick box isn't actually mandatory
- Monthly interest doesn't calculate properly
- Clicking on username in top right leads to an error
