package amazonoa;

//https://leetcode.com/problems/most-expensive-item-that-can-not-be-bought/description/?envType=company&envId=amazon&favoriteSlug=amazon-thirty-days
public class MostExpensiveItem {
    public int mostExpensiveItem(int primeOne, int primeTwo) {
        // Using the formula for the largest non-representable number
        // in the Frobenius coin problem for two relatively prime numbers:
        return (primeOne * primeTwo) - primeOne - primeTwo;
    }
}
