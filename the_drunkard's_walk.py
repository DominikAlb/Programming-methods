#Dominik Albiniak
import random
import math
import numpy as np
#   instalacja matplotlib:
#   Do konsoli wpisac:
#   python -mpip install -U pip
#   python -mpip install -U matplotlib
#   program symulujacy spacer losowy i zliczajacy 
#   moment pierwszego powrotu do punktu poczatkowego
from matplotlib import pyplot as plt 
def step(count):
    value = random.randint(0,1)
    if value == 0:
        count = count - 1
    else:
        count = count + 1
    return count
steps = [1] * 1000
for i in range(1000):
    drinker = 0
    drinker = step(drinker)
    while(drinker != 0):
        drinker = step(drinker)
        steps[i] = steps[i] + 1
plt.hist(steps)
# w razie wolnej mocy obliczeniowej komputera zakomentowac ponizsza komende:
plt.xticks(np.arange(min(steps), max(steps)+1, 100))
plt.show()
    
