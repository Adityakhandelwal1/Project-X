package com.brs.projectx.stockparser.service;


import java.io.FileWriter;
import java.io.IOException;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class JavaScraperService {

	final String url = "https://in.finance.yahoo.com/most-active?offset=1&items_per_page=10";
	private String urlForNewPage = "https://in.finance.yahoo.com";
	private String urlForYearlyRange;
	private Element tableElement;
	private Elements tableHeaderElements;
	private Elements tableBodyElements;
	private String stockInfoTablePath = "stockparser\\src\\main\\resources\\output1.csv";
	private FileWriter csvWriter = null;
	private String tableElementString = null;
	private Elements newPageElements;
	
	private void updateCSVtableElements(String webLink)
	{
		Document document = null;
		try {
			 document = Jsoup.connect(webLink).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		tableElement = document.select("table").first();
		tableHeaderElements = tableElement.select("thead tr th");
		tableBodyElements = tableElement.select("td");
	}
	
	private String removingCommas(String string)
	{
		String replacedString = string;
		for(int i = 0; i<string.length(); i++)
		{
			if(string.charAt(i)==',')
			{
				replacedString = string.substring(0, i) + string.substring(i+1);
			}
		}
		return replacedString;
	}
	private String convertCommaToDot(String string)
	{
		String replacedString = string;
		for(int i = string.length()-1; i >=0 ; i--)
		{
			if(string.charAt(i) == ',')
			{
				replacedString = string.substring(0,i) + '.' + string.substring(i+1);
				break;
			}
		}
		return replacedString;
	}
	public void updateStockCSVTable()
	{
		updateCSVtableElements(url); 
		try {
			csvWriter = new FileWriter(stockInfoTablePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int numberOfColumns = 1 ; numberOfColumns < tableHeaderElements.size(); numberOfColumns++)
		{
			
			if(numberOfColumns == 8)
				continue;
			else if(numberOfColumns == 5)
			{
				try {
					csvWriter.append(tableHeaderElements.get(numberOfColumns+2).text());
					csvWriter.append(",");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(numberOfColumns == 7)
			{
				try {
					csvWriter.append(tableHeaderElements.get(numberOfColumns-2).text());
					csvWriter.append(",");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				try {
					csvWriter.append(tableHeaderElements.get(numberOfColumns).text());
					csvWriter.append(",");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		try {
			csvWriter.append("\n");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int check = 0;
		for(int i = 1; i< tableBodyElements.size();i++)
		{
			check++;
			if(check == 9)
			{
				urlForYearlyRange = getLinkPerStock(tableBodyElements.get(i-check));
				String urlForStockPage = getUrlForStockPage(urlForNewPage + urlForYearlyRange);
				i+=1;
				check = 0;
				try {
					csvWriter.append(removingCommas(urlForStockPage));
					csvWriter.append(",");
					csvWriter.append("\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(check!=8)
			{
				if(check == 5)
					tableElementString = convertCommaToDot(tableBodyElements.get(i+2).text());
				else if(check==7)
					tableElementString = convertCommaToDot(tableBodyElements.get(i-2).text());
				else if(check==2) {
					tableElementString = removeDots(tableBodyElements.get(i).text());
					tableElementString = convertCommaToDot(tableElementString);
				}
				
				else
					tableElementString = convertCommaToDot(tableBodyElements.get(i).text());
				try {
					csvWriter.append(tableElementString);
					csvWriter.append(",");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
				continue;
		}		 
		try {
			csvWriter.flush();
			csvWriter.close();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}
	private String getLinkPerStock(Element tableBodyElement)
	{
		Element hrefElement = tableBodyElement.select("a").first();
		return hrefElement.attr("href");
	}
	private String getUrlForStockPage(String newUrl)
	{
		Document document = null;
		try {
			document = Jsoup.connect(newUrl).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		newPageElements = document.select("td");
		document = null;
		return newPageElements.get(11).text();
	}
	private String removeDots(String string)
	{
		String replacedString = string;
		for(int i = 0; i <string.length() ; i++)
		{
			if(string.charAt(i) == '.')
			{
				replacedString = string.substring(0,i) + string.substring(i+1);
				break;
			}
		}
		return replacedString;
	}
}

