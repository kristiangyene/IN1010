public class Post{
  String beskjed = "Hei, " + this.hentMottaker() + "! Det var hyggelig å se deg igjen.";
  String mottaker;
  int postId;

  public Post(String mottaker, int postId){
    this.beskjed = beskjed;
    this.mottaker = mottaker;
    this.postId = postId;
  }

  public String hentBeskjed(){
    return beskjed;
  }
  public int hentId(){
    return postId;
  }
  public String hentMottaker(){
    return mottaker;
  }
  public void setBeskjed(String beskjed){
    this.beskjed = beskjed;
  }
  public void setMottaker(String mottaker){
    this.mottaker = mottaker;
  }
}
