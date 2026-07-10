class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length-1;
        int[] res = new int[2];
        //factoriser le number[left]+number[right] dans une variable pour eviter de le recalculer
        //int sum = numbers[left]+numbers[right];
        // mieux vaut faire while(left<right) pour eviter de faire un while infini dans le cas où la solution n'existe pas
        //while(left<right)
        while((numbers[left]+numbers[right])!= target){
            if((numbers[left]+numbers[right])>target){
                right--;
            }
            else{
                left++;
            }
        }
        res[0]= left+1;
        res[1]= right+1;
        //pourquoi ne pas retourner directement le tableau sans le stocker dans une variable res
        //return new int[]{left+1,right+1};
        return res;

        //s'il n'y pas de solution mieux vaut implementer un throw new IllegalArgumentException("No solution found") pour signaler qu'il n'y a pas de solution
    }
}