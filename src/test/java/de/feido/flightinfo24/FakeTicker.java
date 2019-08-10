package de.feido.flightinfo24;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.google.common.base.Ticker;

public class FakeTicker extends Ticker {

	private final AtomicLong nanos = new AtomicLong();

	public FakeTicker advance(long time, TimeUnit timeUnit) {
		nanos.addAndGet(timeUnit.toNanos(time));
		return this;
	}

	@Override
	public long read() {
		return nanos.getAndAdd(0);
	}

}
