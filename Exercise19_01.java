/**  Professor: Dr. Eickemeyer
*    03.08.2018
*    Exercise19_01
*/

public class GenericStack<E> {
  E[] list = (E[]) Array.newInstance();



  public int getSize(){
    return list.length;
  }

  public E peek() {
    return list.get(getSize() - 1);
  }

  public void push(E o){
    int placement = list.getSize();
    if(list.length > 5){

    }else{
      list[placement] = o;
    }
  }

  public E pop(){
    E o = list.get(getSize() - 1);
    list.remove(getSize() - 1);
    return o;
  }

  public boolean isEmpty(){
    return list.isEmpty();
  }

  @Override
  public String toString() {
    return "stack: " + list.toString();
  }
}
