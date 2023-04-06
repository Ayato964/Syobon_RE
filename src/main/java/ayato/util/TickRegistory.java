package ayato.util;

public class TickRegistory<T> {
    private final T t;
    private final TickHelper tick_method;
    public  TickRegistory(T t, TickHelper tick){
        this.t = t;
        tick_method = tick;
    }

    public void run_tick(){
            tick_method.tick(t);
    }
    public void remove(){
        Tick.getInstance().removeAnimation(this);
    }
    public static <A>  TickRegistory createTicker(A r, TickHelper t){
        TickRegistory tr = new TickRegistory(r, t);
            return tr;
    }
    public static <A> TickRegistory createTickerAnimation(A r, TickHelper t){
        TickRegistory tr = new TickRegistory(r, t);
        Tick.getInstance().addAnimation(tr);
        return tr;
    }
}
