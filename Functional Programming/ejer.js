// banner :: string -> IO () <-- IO = side effects, () = void
const banner = text => console.log(`=====================[${text}]=====================`);
banner("hello");
//
banner("a function");
// add :: number -> number -> number
const add = (a,b) => a + b;
console.log(`2 + 3 = ${add(2, 3)}`);

banner("higher order functions");
// add2 :: number -> number
const add2 = a => add(2, a);
console.log(`[1,2,3,4].map(add2) = ${[1,2,3,4].map(add2)}`);

banner("implementing our own map on lists");
// lmap :: (a -> b) -> [a] -> [b]
const lmap = (f, a) => {
    if (!a.length) return [];
    const [ha, ...ta] = a;
    return [f(ha), ...lmap(f, ta)];
};
console.log(`lmap(add2, [1,2,3,4]) = ${lmap(add2, [1,2,3,4])}`);

banner("composing functions: manual");
// square :: number -> number
const square = n => n * n;
console.log(`lmap(square, lmap(add2, [1,2,3,4])) = ${lmap(square, lmap(add2, [1,2,3,4]))}`);

// add2thenSquare1 :: number -> number
const add2thenSquare1 = n => square(add2(n));
console.log(`lmap( (square · add2), [1,2,3,4]) = ${lmap(add2thenSquare1, [1, 2, 3, 4])}`);

banner("composing functions, the cool way");
// compose :: (b -> c) -> (a -> b) -> (a -> c)
const compose = (f, g) => a => f(g(a));
// add2thenSquare2 :: number -> number
const add2thenSquare2 = compose(square, add2);
console.log(`lmap( (square · add2), [1,2,3,4]) = ${lmap(add2thenSquare2, [1,2,3,4])}`);

const pipe = (g,f)=>compose(f,g);
const add2thenSquare3 = compose(add2,square);
console.log(`lmap( (square | add2), [1,2,3,4]) = ${lmap(add2thenSquare3, [1,2,3,4])}`);

banner("currying, manual way")
const add_=a=>b=>a+b;
console.log(`add_(2)(3) = 2+3 = ${add_(2)(3)}`);


// addCurriedManual :: number -> number -> number
const addCurriedManual = a => b => add(a, b);
// add2curried1 :: number -> number
const add2curried1 = addCurriedManual(2);
console.log(`2 + 3 = ${addCurriedManual(2)(3)}`);
banner("currying automatic");
const curry = (f, arr=[]) =>
    (...args) => (
        a => a.length === f.length
            ? f(...a)
            : curry(f, a)
    )([...arr, ...args]);
// addCurriedAuto :: number -> number -> number
const addCurriedAuto = curry(add);
// add2CurriedAuto :: number -> number
const add2CurriedAuto = addCurriedAuto(2);
console.log(`lmap(add2curried1, [1,2,3,4]) = ${lmap(add2curried1, [1,2,3,4])}`);

banner("flatMap");
// OOTB flatMap :: [a] -> (a -> [b]) -> [b]
console.log(`[1,2,3,4].map(a => [a,a]) = ${[1,2,3,4].map(a => [a, a])}`);
console.log(`[1,2,3,4].flatMap(a => [a,a]) = ${[1,2,3,4].flatMap(a => [a, a])}`);


console.log(`${[1,2,3,4].flatMap(a => [5,6,7,8].map(b => `(${a}, b)`))}`);
