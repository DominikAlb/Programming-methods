#Dominik Albiniak
import random
#   instalacja matplotlib:
#   Do konsoli wpisac:
#   python -mpip install -U pip
#   python -mpip install -U matplotlib
from matplotlib import pyplot as plt 
import math

n = [5, 10, 25, 100, 250, 500]
p = []
X = [0] * 1000
for i in range(5):
        p.append(float(input()))
for i in range(len(n)):
    for j in range(1000):
        for k in range(n[i]):
            X[j] = X[j] + p[random.randint(0, 4)]
    plt.hist(X)
    plt.show()

