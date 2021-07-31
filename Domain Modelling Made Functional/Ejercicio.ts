enum FooBarBaz {Foo = "Foo", Bar = "Bar", Baz = "Baz" } // string representation is for logging
type FooBarBaz123 = FooBarBaz | 1 | 2 | 3
const logFooBar123 = (x: FooBarBaz123):void => console.log(`${x}`)
logFooBar123(1) // logs 1
logFooBar123(FooBarBaz.Bar) // logs Bar