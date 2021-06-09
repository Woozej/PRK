package application;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PaintingClass {
	private StringProperty title;
	private StringProperty author;
	private StringProperty size;
	private DoubleProperty surface;
	private DoubleProperty weight;
	private DoubleProperty price;
	
	PaintingClass(String title,String author,String size,Double weight,Double price ){
		this.title = new SimpleStringProperty(title);
		this.author = new SimpleStringProperty(author);
		this.size = new SimpleStringProperty(size);
		String[] splittedSize = size.split("x");
		if(splittedSize.length == 2) {
			this.surface = new SimpleDoubleProperty(Double.parseDouble(splittedSize[0]) * Double.parseDouble(splittedSize[1]));
		}
		else {
			this.surface = new SimpleDoubleProperty(Math.PI * Math.pow(Double.parseDouble(splittedSize[0]),2));
		}
		this.weight = new SimpleDoubleProperty(weight);
		this.price = new SimpleDoubleProperty(price);
	}
	public String getTitle() {
		return this.title.get();
	}
	public StringProperty titleProperty(){
		return title;
	}
	
	public String getAuthor() {
		return this.author.get();
	}
	public StringProperty authorProperty(){
		return author;
	}
	
	public String getSize() {
		return this.size.get();
	}
	public StringProperty sizeProperty(){
		return size;
	}
	
	public Double getWeight() {
		return this.weight.get();
	}
	public DoubleProperty weightProperty(){
		return weight;
	}
	
	public Double getPrice() {
		return this.price.get();
	}
	public DoubleProperty priceProperty(){
		return price;
	}
	
	public Double getSurface() {
		return this.surface.get();
	}
	public DoubleProperty surfaceProperty(){
		return surface;
	}
	
}
