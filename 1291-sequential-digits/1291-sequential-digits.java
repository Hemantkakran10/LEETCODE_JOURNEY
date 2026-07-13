class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer>list=new ArrayList<>();
        String str="123456789";
        for(int start=0;start<str.length();start++){
            for(int end=start+1;end<=str.length()&&start!=end;end++){
                int digit=Integer.parseInt(str.substring(start,end));
                if(digit>=low&&digit<=high)list.add(digit);
            }
        }
        Collections.sort(list);
        return list;
    }
}