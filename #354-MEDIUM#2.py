complexitiesDict = {0: 0, 1: 1, 2: 2, 3: 3, 4: 4, 5: 5} 
#Complexities of numbers n up to 5 

def getFactorPairs(n):  
    return([ (i, n//i) for i in range(2, int(n**0.5)+1) if n%i==0]) 
def getSumPairs(n):
    return([ (i, n-i) for i in range(1, n//2+1)])

def getComplexity(n):
    #Gets complexity of number 
    #Will fail with RecursionDepthExceeded if run on large #'s 
    if n>=0 and n<=5:
        return(n)
    else:
        try:
            return(complexitiesDict[n])
        except KeyError:
            factorPairs = getFactorPairs(n)
            print("factorPairs: ", factorPairs)
            sumPairs = getSumPairs(n)
            print("sumPairs: ", sumPairs)
            sumFactorPairs = factorPairs + sumPairs
            print("sumFactorPairs: ", sumFactorPairs)
            complexity = min( [ getComplexity(k[0]) + getComplexity(k[1]) for k in sumFactorPairs ])

            complexitiesDict[n] = complexity
    return(complexity)

def getSumComplexity(n):
    #Gets sums of all complexities of numbers under and including a limit. 
    return sum([getComplexity(n) for i in range(1, n+1)])

print(getSumComplexity(5678))