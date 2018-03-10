package Cache;

import MyORM.DataSet;

import java.lang.ref.SoftReference;
import java.util.*;
import java.util.function.Function;


public class CacheEngine<T extends DataSet> implements CacheEngineInterface<T> {
    private static final int TIME_THRESHOLD_MS = 5;

    private final int maxElements;
    private final long lifeTimeMs;
    private final long idleTimeMs;
    private final boolean isEternal;

    private final Map<Long, SoftReference<T>> elements = new LinkedHashMap<>(100);
    private final Timer timer = new Timer();

    private int hit = 0;
    private int miss = 0;

    public CacheEngine(int maxElements, long lifeTimeMs, long idleTimeMs, boolean isEternal) {
        this.maxElements = maxElements;
        this.lifeTimeMs = lifeTimeMs > 0 ? lifeTimeMs : 0;
        this.idleTimeMs = idleTimeMs > 0 ? idleTimeMs : 0;
        this.isEternal = lifeTimeMs == 0 && idleTimeMs == 0 || isEternal;
    }

    public void put(T element,Long key) {
        if (elements.size() == maxElements) {
            Long firstKey = elements.keySet().iterator().next();
            elements.remove(firstKey);
        }

        SoftReference<T> elm=new SoftReference<T>(element);
        elements.put(key, elm);
        System.out.println("Запись попала в кэш");

        if (!isEternal) {
            if (lifeTimeMs != 0) {
                TimerTask lifeTimerTask = getTimerTask(key, lifeElement -> lifeElement.getCreationTime() + lifeTimeMs);
                timer.schedule(lifeTimerTask, lifeTimeMs);
            }
            if (idleTimeMs != 0) {
                TimerTask idleTimerTask = getTimerTask(key, idleElement -> idleElement.getLastAccessTime() + idleTimeMs);
                timer.schedule(idleTimerTask, idleTimeMs, idleTimeMs);
            }
        }
    }

    public T get(Long key) {
        SoftReference<T> element = elements.get(key);
        T elm=null;
        if (element != null) {
            hit++;
            elm=element.get();
            System.out.println("Запись возвращена из кэша");
        } else {
            miss++;
        }

        return elm;
    }
    public String getValues(){return elements.values().toString();}

    public int getHitCount() {
        return hit;
    }

    public int getMissCount() {
        return miss;
    }

    public void dispose() {
        timer.cancel();
    }

    private TimerTask getTimerTask(final Long key, Function<T, Long> timeFunction) {
        return new TimerTask() {
            @Override
            public void run() {
                SoftReference<T> element = elements.get(key);
                if (element == null || isT1BeforeT2(timeFunction.apply(element.get()), System.currentTimeMillis())) {
                    elements.remove(key);
                    this.cancel();
                }
            }
        };
    }


    private boolean isT1BeforeT2(long t1, long t2) {
        return t1 < t2 + TIME_THRESHOLD_MS;
    }
}