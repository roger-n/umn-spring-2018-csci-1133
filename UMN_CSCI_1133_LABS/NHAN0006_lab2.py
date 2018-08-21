class Zillion():
	def __init__(self, digit):
		self.counter = []
		for i in digit:
			if i.isdigit() or i == ',' or i == ' ':
				if i.isdigit():
					self.counter += [int(i)]
			else: 
				raise RuntimeError
				break
		if len(self.counter) == 0:
			raise RuntimeError
	def increment(self):
		finished = False
		i = len(self.counter)-1
		while not finished:
			if self.counter[i] != 9 and i >= 0:
				self.counter[i] += 1
				finished = True
			elif i >= 0:
				self.counter[i] = 0
				i -= 1
			else:
				self.counter.insert(0, 1)
				finished = True
	def isZero(self):
		for i in self.counter:
			if i != 0:
				return False
		return True
	def toString(self):
		counterStr = ''
		i = 0
		while i < len(self.counter):
			counterStr =  counterStr + str(self.counter[i])
			i += 1
		return counterStr
			
			
			
		
				
#tests
try:
  z = Zillion('')
except RuntimeError:
  print('Empty string')

# It must print 'Empty string' without apostrophes. 2 points.

try:
  z = Zillion(' , ')
except RuntimeError:
  print('No digits in the string')

# It must print 'No digits in the string' without apostrophes. 2 points.

try:
  z = Zillion('1+0')
except RuntimeError:
  print('Non-digit in the string')

# It must print 'Non-digit in the string' without apostrophes. 2 points.

try:
  z = Zillion('0')
except RuntimeError:
  print('This must not be printed')

#  It must print nothing. 2 points.

print(z.isZero())    #  It must print True. 2 points.

try:
  z = Zillion('000000000')
except RuntimeError:
  print('This must not be printed')

#  It must print nothing. 2 points.

print(z.isZero())    #  It must print True. 2 points.

try:
  z = Zillion('000 000 000')
except RuntimeError:
  print('This must not be printed')

#  It must print nothing. 2 points.

print(z.isZero())    #  It must print True. 2 points.

try:
  z = Zillion('997')
except RuntimeError:
  print('This must not be printed')

#  It must print nothing.  2 points.

print(z.isZero())    #  It must print False. 2 points.

print(z.toString())  #  It must print 997. 2 points.

z.increment()

print(z.toString())  #  It must print 998. 2 points.

z.increment()

print(z.toString())  #  It must print 999. 2 points.

z.increment()

print(z.toString())  #  It must print 1000. 2 points.

try:
  z = Zillion('0 9,9 9')
except:
  print('This must not be printed')

#  It must print nothing.  3 points.

z.increment()
print(z.toString())  #  It must print 1000. 2 points.
