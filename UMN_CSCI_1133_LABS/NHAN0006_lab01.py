def solve(v, q):
    if (isInside(v, right(q))) == True:
        return solving(v, (right(q), op(q), left(q)))
    elif isInside(v, left(q)) == True:
        return solving(v, (left(q), op(q), right(q)))


# else:
# return None

def solving(v, q):
    if type(left(q)) is tuple:
        if op(left(q)) == '+':
            return solvingAdd(v, q)
        elif op(left(q)) == '-':
            return solvingSubtract(v, q)
        elif op(left(q)) == '*':
            return solvingMultiply(v, q)
        elif op(left(q)) == '/':
            return solvingDivide(v, q)
        else:
            return None
    else:
        return q


def isInside(v, e):
    if v == '+' or v == '-' or v == '*' or v == '/':
        return False
    i = 0
    while i < len(e):
        if e[i] == v:
            return True
        elif type(e[i]) is tuple:
            if isInside(v, e[i]) == True:
                return True
        i += 1
    return False


def solvingAdd(v, q):
    qLeft = reorder(v, left(q))
    return solving(v, (left(qLeft), '=', (right(q), '-', right(qLeft))))


def solvingSubtract(v, q):
    if isInside(v, right(left(q))) == True:
        return solving(v, (right(left(q)), '=', (left(left(q)), '-', right(q))))
    elif isInside(v, left(left(q))) == True:
        qLeft = reorder(v, left(q))
        return solving(v, (left(qLeft), '=', (right(q), '+', right(qLeft))))


def solvingMultiply(v, q):
    qLeft = reorder(v, left(q))
    return solving(v, (left(qLeft), '=', (right(q), '/', right(qLeft))))


def solvingDivide(v, q):
    if isInside(v, right(left(q))) == True:
        return solving(v, (right(left(q)), '=', (left(left(q)), '/', right(q))))
    elif isInside(v, left(left(q))) == True:
        qLeft = reorder(v, left(q))
        return solving(v, (left(qLeft), '=', (right(q), '*', right(qLeft))))


def left(e):
    return e[0]


def op(e):
    return e[1]


def right(e):
    return e[2]


def reorder(v, q):
    # pretty much a clone of solve, but doesn't return with solving
    if (isInside(v, right(q))) == True:
        return (right(q), op(q), left(q))
    elif isInside(v, left(q)) == True:
        return (left(q), op(q), right(q))


print(solve('x', (('a', '+', 'x'), '=', 'c')))
#  ('x', '=', ('c', '-', 'a'))  2 points

print(solve('x', (('x', '+', 'b'), '=', 'c')))
#  ('x', '=', ('c', '-', 'b'))  2 points

print(solve('x', (('a', '-', 'x'), '=', 'c')))
#  ('x', '=', ('a', '-', 'c'))  2 points

print(solve('x', (('x', '-', 'b'), '=', 'c')))
#  ('x', '=', ('c', '+', 'b'))  2 points

print(solve('x', (('a', '*', 'x'), '=', 'c')))
#  ('x', '=', ('c', '/', 'a'))  2 points

print(solve('x', (('x', '*', 'b'), '=', 'c')))
#  ('x', '=', ('c', '/', 'b'))  2 points

print(solve('x', (('a', '/', 'x'), '=', 'c')))
#  ('x', '=', ('a', '/', 'c'))  2 points

print(solve('x', (('x', '/', 'b'), '=', 'c')))
#  ('x', '=', ('c', '*', 'b'))  2 points

print(solve('y', ('y', '=', (('m', '*', 'x'), '+', 'b'))))
# ('y', '=', (('m', '*', 'x'), '+', 'b'))  2 points

print(solve('x', ('y', '=', (('m', '*', 'x'), '+', 'b'))))
# ('x', '=', (('y', '-', 'b'), '/', 'm'))  2 points

print(solve('a', (('b', '+', 'c'), '=', ('d', '*', (('a', '/', 'e'), '-', 'f')))))
# ('a', '=', (((('b', '+', 'c'), '/', 'd'), '+', 'f'), '*', 'e'))  5 points
