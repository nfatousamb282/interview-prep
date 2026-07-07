import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> seen = new HashMap<>();
        int[] res= new int[2];
        for(int i=0; i< nums.length; i++){
            if(seen.get(target-nums[i])==null){
                seen.put(nums[i],i);
            }else{
                res[0]=seen.get(target-nums[i]);
                res[1]=i;
                return res;
            }  
        }
        return res;
    }
}