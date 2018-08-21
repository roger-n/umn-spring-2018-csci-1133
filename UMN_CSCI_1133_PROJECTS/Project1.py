class Random:
    def __init__(self, seed):
        self.a = 7**5
        self.b = 2**31 - 1
        self.current = (self.a * seed) % self.b

    def next(self):
        self.current = (self.a * self.current) % self.b
        return self.current

    def choose(self, objects):
        return objects[self.next() % len(objects)]


class Grammar:
    def __init__(self, seed):
        self.random = Random(seed)
        self.rules = {}

    def rule(self, left, right):
        if left not in self.rules:
            self.rules[left] = [right]
            return
        self.rules[left] = self.rules[left] + [right]
        return

    def generate(self):
        if 'Start' in self.rules:
            return self.generating(('Start',))
        raise RuntimeError

    def generating(self, strings):
        final = ''
        for element in strings:
            if element not in self.rules:
                final += element + ' '
            else:
                #print(self.random.choose(self.rules[element]))
                final += self.generating(self.random.choose(self.rules[element]))
        return final


#Tests

tests = {}
for x in range(0, 10):
    tests[x] = Grammar(x)
    tests[x].rule('Noun', ('cat',))  # 01
    tests[x].rule('Noun', ('boy',))  # 02
    tests[x].rule('Noun', ('dog',))  # 03
    tests[x].rule('Noun', ('girl',))  # 04
    tests[x].rule('Verb', ('bit',))  # 05
    tests[x].rule('Verb', ('chased',))  # 06
    tests[x].rule('Verb', ('kissed',))  # 07
    tests[x].rule('Phrase', ('the', 'Noun', 'Verb', 'the', 'Noun'))  # 08
    tests[x].rule('Story', ('Phrase',))  # 09
    tests[x].rule('Story', ('Phrase', 'and', 'Story'))  # 10
    tests[x].rule('Start', ('Story', '.'))  # 11
    print(tests[x].generate())

tests1 = {}
for x in range(0, 10):
    tests1[x] = Grammar(x)
    tests1[x].rule('Noun', ('noun0',))  # 01
    tests1[x].rule('Noun', ('noun1',))  # 02
    tests1[x].rule('Noun', ('noun2',))  # 03
    tests1[x].rule('Verb', ('verb0',))  # 04
    tests1[x].rule('Verb', ('verb1',))  # 05
    tests1[x].rule('Verb', ('verb2',))  # 06
    tests1[x].rule('Phrase', ('the', 'Noun', 'Verb', 'the', 'Noun'))  # 07
    tests1[x].rule('Story', ('Phrase',))  # 08
    tests1[x].rule('Story', ('Phrase', 'and', 'Story'))  # 09
    tests1[x].rule('Start', ('Story', '.'))  # 10
    print(tests1[x].generate())
