def Sort(t):
    if len(t) == 0:
        return t
    return Sort(Remove(t, Maximum(t))) + (Maximum(t),)


def Maximum(t):
    if len(t) == 1:
        return t[0]
    else:
        if t[0] > t[1]:
            if len(t) > 2:
                return Maximum((t[0],) + t[2:len(t)])
            else:
                return Maximum((t[0],))
        else:
            return Maximum(t[1:len(t)])


def Remove(t, e):
    if len(t) == 0:
        return t
    else:
        if t[0] == e:
            return t[1:len(t)]
        else:
            return (t[0],) + Remove(t[1:len(t)], e)


print(Maximum((1,)))                      #  1                            2 pt.
print(Maximum((1, 2)))                    #  2                            2 pt.
print(Maximum((1, 1)))                    #  1                            2 pt.
print(Maximum((1, 2, 3)))                 #  3                            2 pt.

print(Remove((), 1))                      #  ()                           2 pt.
print(Remove((1,), 1))                    #  ()                           2 pt.
print(Remove((0, 1), 0))                  #  (1,)                         2 pt.
print(Remove((0, 1, 2, 1, 3), 1))         #  (0, 2, 1, 3)                 2 pt.
print(Remove((0, 1, 2, 1, 3), 2))         #  (0, 1, 1, 3)                 2 pt.
print(Remove((1, 2, 3), 3))               #  (1, 2)                       2 pt.

print(Sort(()))                           #  ()                           2 pt.
print(Sort((0,)))                         #  (0,)                         2 pt.
print(Sort((0, 1)))                       #  (0, 1)                       2 pt.
print(Sort((1, 0)))                       #  (0, 1)                       2 pt.
print(Sort((0, 0, 1)))                    #  (0, 0, 1)                    2 pt.
print(Sort((0, 1, 0)))                    #  (0, 0, 1)                    2 pt.
print(Sort((0, 0, 1)))                    #  (0, 0, 1)                    2 pt.

print(Sort((9, 8, 7, 6, 5, 4, 3, 2, 1)))  #  (1, 2, 3, 4, 5, 6, 7, 8, 9)  2 pt.
print(Sort((1, 2, 3, 4, 5, 6, 7, 8, 9)))  #  (1, 2, 3, 4, 5, 6, 7, 8, 9)  2 pt.
print(Sort((1, 2, 1, 4, 2, 5, 4, 5, 3)))  #  (1, 1, 2, 2, 3, 4, 4, 5, 5)  2 pt.
