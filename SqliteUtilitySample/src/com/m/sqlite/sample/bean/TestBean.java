package com.m.sqlite.sample.bean;

import com.m.support.sqlite.annotation.PrimaryKey;

public class TestBean {

    @PrimaryKey(column = "id")
    private String id;

	private String str;

    private long longt;

    private char chart;

    private double doublet;

    private boolean booleant;

    private byte[] bytes;

    private int intt;

    private float floatt;

    private byte bytet;

    private TestBean tbean;

    private short shortt;

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getLongt() {
        return longt;
    }

    public void setLongt(long longt) {
        this.longt = longt;
    }

    public char getChart() {
        return chart;
    }

    public void setChart(char chart) {
        this.chart = chart;
    }

    public double getDoublet() {
        return doublet;
    }

    public void setDoublet(double doublet) {
        this.doublet = doublet;
    }

    public boolean isBooleant() {
        return booleant;
    }

    public void setBooleant(boolean booleant) {
        this.booleant = booleant;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public int getIntt() {
        return intt;
    }

    public void setIntt(int intt) {
        this.intt = intt;
    }

    public float getFloatt() {
        return floatt;
    }

    public void setFloatt(float floatt) {
        this.floatt = floatt;
    }

    public byte getBytet() {
        return bytet;
    }

    public void setBytet(byte bytet) {
        this.bytet = bytet;
    }

    public TestBean getTbean() {
        return tbean;
    }

    public void setTbean(TestBean tbean) {
        this.tbean = tbean;
    }

    public short getShortt() {
        return shortt;
    }

    public void setShortt(short shortt) {
        this.shortt = shortt;
    }
}
