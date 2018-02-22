# Lightrail Rules

Lightrail Rules is a simple language for defining powerful configuration logic.  It is used to evaluate [Redemption Rules](../use-cases/redemption-rules.md) and [Reactions](reactions.md).

## Syntax

Lightrail Rules follow the syntax of most programming languages.  Mathematical, logical, string and comparison operators behave as usual.

<!-- Use javascript as the syntax language because it's close enough. -->
```javascript
-1
1 + 2
3 - 4
5 * 6
7 / 8
9 % 10
!true
true && false
true || false
'foo' + 'bar' == 'foobar'
1 < 2
3 <= 4
6 > 5
8 >= 7
9 == 9
10 != 11
true ? 'yes' : 'no'
```

Parentheses can be used to group operations.

```javascript
4 * (1 + 2)
(1 + 2 + 3) == 6
(9 < 5) || (3 < 5)
```

Lists (arrays) are accessed with brackets and are 0-indexed.

```javascript
myList[0]
myList[1]
myList[variableName]
```

Lists can be created with brackets.

```javascript
[]
[1, 2, 3]
['alpha', 'beta', 'charlie']
```

Maps (dictionaries, objects) can be accessed with dot notation or bracket notation.

```javascript
myMap.child
myMap['child']
myMap[variableName]
```

Functions are global.  Their names are case-sensitive.  They can be accessed like the global function they are, or treated like methods with the first argument acting as the object the method is on.  This second form is more convenient for chaining.  Some functions can accept a variable number of arguments or multiple types.  For a complete list of functions see the [functions section](#functions).

```javascript
max(1, 2)
max(1, 2, 3, 4)

sum(1, 2)
sum([1, 2])
sum([1, 2], 3, [4, [5, 6]])

size(['a', 'b', 'c'])
['a', 'b', 'c'].size()

size('hello world')
'hello world'.size()

sum([1, 2, 3, 4])
[1, 2, 3, 4].sum()

round(sum([1.23, 4.56, 7.89]))
[1.23, 4.56, 7.89].sum().round()
```

Some functions operate on collections and take a lambda parameter.  These are the most powerful functions.  Lambdas are written using fat arrow `=>` notation and are single statements that return a value, just like rules.  The parameter names can be any combination of letters.  If the lambda only has one argument the parentheses can be skipped.

```javascript
[1, 2, 3, 4, 5].filter(x => x % 2 == 0) → [2, 4]
[1, 2, 3, 4, 5].find(x => x % 2 == 0) → 2
[1, 2, 3, 4, 5].findIndex(x => x % 2 == 0) → 1
[1, 2, 3, 4, 5].some(x => x % 2 == 0) → true
[1, 2, 3, 4, 5].every(x => x % 2 == 0) → false
[1, 2, 3, 4, 5].map(x => x * 2) → [2, 4, 6, 8, 10]
[1, 2, 3, 4, 5].reduce((accumulator, value) => accumulator + value, 0) → 15
```

Operators and functions will coerce values to the type that best fits what's needed following rules similar to EcmaScript.  For more details see the [type coercion section](#type-coercion).

```javascript
!null → true
!0 → true
!196 → false
'1' == 1 → true
3 > '2' → true
4 + '5' → '45'
4 - '5' → -1
true + 1 → 2
max(1, 2, '3') → 3
max(null, null) → 0
```

## Functions

### abs

`abs(value: number) → number`

Get the absolute value of the number.

```javascript
abs(1) → 1
abs(-1) → 1
```

### ceil

`ceil(value: number) → number`

Get the smallest integer that is greater than or equal to the number.

```javascript
ceil(1) → 1
ceil(1.2345) → 2
ceil(-12.34) → -12
```

### every

`every(list: any[], condition: (listElement: any) => boolean) → boolean`

Test whether every item in a list meets the given condition.

```javascript
every([1, 2, 3], x => x > 0) → true
['a', 'b', 'c', 'd'].every(x => x == 'a') → false
```

### filter

`filter(list: any[], condition: (listElement: any) => boolean) → any[]`

Get a subset of a list whose members meet the given condition.

```javascript
filter([1, 2, 3], x => x % 2 == 0) → [2]
['a', 'b', 'c', 'd'].filter(x => x != 'a') → ['b', 'c', 'd']
```

### find

`find(list: any[], condition: (listElement: any) => boolean) → any`

Get the first element of a list that meets the given condition.  Returns null if the condition is never met.

```javascript
find([1, 2, 3], x => x % 2 == 0) → 2
['a', 'b', 'c', 'd'].find(x => x != 'a') → 'b'
['a', 'b', 'c', 'd'].find(x => x == 'e') → null
```

### findIndex

`findIndex(list: any[], condition: (listElement: any) => boolean) → number`

Get the index of the first element of a list that meets a given condition.  Returns -1 if the condition is never met.

```javascript
findIndex([1, 2, 3], x => x % 2 == 0) → 1
['a', 'b', 'c', 'd'].findIndex(x => x != 'a') → 1
['a', 'b', 'c', 'd'].findIndex(x => x == 'e') → -1
```

### floor

`floor(value: number) → number`

Get the greatest integer that is less than or equal to the number.

```javascript
floor(1) → 1
floor(1.2345) → 1
floor(-12.34) → -13
```

### isNaN

`isNaN(value: number) → boolean`

Test whether the number is NaN. NaN is a special case of number resulting from the operation `0 / 0`.

```javascript
isNaN(0 / 0) → true
isNaN(1 / 0) → false
isNaN("NaN") → false
isNaN(null) → false
```

### isNull

`isNull(variable: any) → boolean`

Test whether the value is null.

```javascript
isNull(null) → true
isNull(123) → false
isNull('') → false
isNull('null') → false
```

### keys

`keys(m: map) → string[]`

Get the keys of the map in a list.

```javascript
keys(null) → []
keys(metadata.foo) → ['itemId', 'size', 'quantity']
```

### map

`map(list: any[], (listElement: any) => any) → any[]`

Create a new list by applying the operation to each member of the list.

```javascript
map(null) → []
map([1, 2, 3], x => x * 3) → [3, 6, 9]
['a', 'b', 'c'].map(x => x + x + x) → ['aaa', 'bbb', 'ccc']
```

### max

`max(...values: number | number[]) → number`

Get the greatest number from the numbers and lists of numbers.

```javascript
max(0) → 0
max(1, -1) → 1
max(1, [2, -11]) → 2
max(1, [2, -11], [[99, -88], 23]) → 99
```

### min

`min(...values: number | number[]) → number`

Get the smallest number from the numbers and lists of numbers.

```javascript
min(0) → 0
min(1, -1) → -1
min(1, [2, -11]) → -11
min(1, [2, -11], [[99, -88], 23]) → -88
```

### reduce

`reduce(list: any[], callback: (accumulator: any, listElement: any, listElementIndex: number, list: any[]) => any, any) → any`

Get a single value from the list by applying an operation to each element of the list and an accumulator.

```javascript
reduce([1, 1, 2, 3, 5, 8], (accumulator, item) => accumulator + item, 0) → 20
[8, 16, 4, 32, 2, 64, 1].reduce((accumulator, item) => accumulator > item ? accumulator : item, 0) → 64
```

### round

`round(value: number) → number`

Round the number to the nearest integer, where any number ending in exactly .5 is rounded up.

```javascript
round(1) → 1
round(1.49) → 1
round(12.5) → 13
round(13.5) → 14
```

### roundBankers

`roundBankers(value: number) → number`

Round the number to the nearest integer, where any number ending in exactly .5 is rounded to the nearest even integer.

```javascript
roundBankers(1) → 1
roundBankers(1.49) → 1
roundBankers(12.5) → 12
roundBankers(13.5) → 14
```

### size

`size(value: string | number) → number`

Get the length of the list or the number of characters in a string.

```javascript
size('asdf') → 4
size('') → 0
'hello world'.size() → 11
size([]) → 0
size([1, 2, 3]) → 3
['one', 'two', 'three'].size() → 3
```

### some

`map(list: any[], condition: (listElement: any) => boolean) → boolean`

Test whether some item in a list meets the given condition.

```javascript
some([1, 2, 3], x => x > 0) → true
['a', 'b', 'c', 'd'].some(x => x == 'a') → true
```

### substring

`substring(haystack: string, start: number = 0, end: number = maxint) → string`

Get a subset of the string.  If only one number is specified the subset will be from that index inclusive to the end.  If the two numbers are specified the subset will be from the lower inclusive to the higher exclusive.

```javascript
substring('foobar', 0) → 'foobar'
substring('foobar', 3) → 'bar'
'foobar'.substring(3, 5) → 'ba'
'foobar'.substring(3, 3) → ''
```

### sum

`sum(...values: number | number[]) → number`

Get the sum total of the numbers and lists of numbers.

```javascript
sum(5) → 5
sum(5, 5, 5) → 15
[1, 2, 3, 4, 5, -10].sum() → 5
```

### toLowerCase

`toLowerCase(s: string) → string`

Get the string with all lower case letters converted to upper case letters in the US locale.

```javascript
toLowerCase('Hello World') → 'hello world'
'HoW aRe YoU'.toLowerCase() → 'how are you'
```

### toUpperCase

`toUpperCase(s: string) → string`

Get the string with all upper case letters converted to lower case letters in the US locale.

```javascript
toUpperCase('Hello World') → 'HELLO WORLD'
'HoW aRe YoU'.toUpperCase() → 'HOW ARE YOU'
```

### values

`values(m: map) → any[]`

Get the values of the map in a list.

```javascript
values(null) → []
values(metadata.foo) → ['33bbb2bf-c270-41d9-ab42-9eeba99fa69c', 'medium', 6]
```

### Operator precedence

Operators are evaluated in the following order, with higher operators being evaluated first.

- grouping `()`
- member access `[]`
- member dot access `.`
- function call `()`
- unary plus `+`, unary minus `-`, logical not `!`
- multiplication `*`, division `/`, modulo `%`
- addition `+`, subtraction `-`
- less than `<`, less than or equal `<=`, greater than `>`, greater than or equal `>=`
- equal `==`, not equal `!=`
- logical and `&&`
- logical or `||`
- ternary conditional `? :`

## Types

- null (`null`)
- boolean (`false`, `true`)
- number (`0`, `1`, `-3`, `123.345`, ...)
- string (`'foo'`, `"bar"`, ...)
- list (`[]`, `[1, 2, 3]`, `['a', 'b', ['c', 'd']]`, ...)
- map (has no literal constructor)
- lambda (`a => a == 1`, ...) (only available as a function argument)

## Type coercion

| target  | source  | value        | condition          |
| ------- | ------- | ------------ | ------------------ |
| boolean | list    | `true`       |                    |
|         | map     | `true`       |                    |
|         | null    | `false`      |                    |
|         | number  | `false`      | number == `0`      |
|         |         | `true`       | number != `0`      |
|         | string  | `false`      | string == `''`     |
|         |         | `true`       | string != `''`     |
| list    | *       | `[]`         |                    |
| map     | *       | an empty map |                    |
| number  | boolean | `0`          | boolean == `false` |
|         |         | `1`          | boolean == `true`  |
|         | list    | `0`          |                    |
|         | map     | `0`          |                    |
|         | null    | `0`          |                    |
|         | string  | †            | †                  |
| string  | boolean | `'false'`    | boolean == `false` |
|         |         | `'true'`     | boolean == `true`  |
|         | list    | ‡            |                    |
|         | map     | ‡            |                    |
|         | number  | ‡            |                    |
|         | null    | `''`         |                    |

† If a string represents a number in string form (eg: `'12'`, `'3.14159'`) then that parsed value will be used. If the string does not represent a number in string form (eg: `'jeff'`, `'twelve'`, `'m365'`) then the value is `0`.

‡ The contents of the list or map will be represented as a string using recursive calls to represent the children as strings.  A number will be represented in parsable form.
