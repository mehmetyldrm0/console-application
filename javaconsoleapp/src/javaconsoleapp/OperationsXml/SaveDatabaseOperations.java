package javaconsoleapp.OperationsXml;

import java.time.LocalDate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javaconsoleapp.dateXml.FileModificationDate;
import javaconsoleapp.entity.Banknote;
import javaconsoleapp.entity.Cross;
import javaconsoleapp.entity.Forex;
import javaconsoleapp.entity.Information;

public class SaveDatabaseOperations {

	void addToDatabase(Element element) {
		
		Configuration configuration = new Configuration().configure();
		
		try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
			try (Session session = sessionFactory.openSession()) {
				Transaction transaction = session.beginTransaction();
				try {

					session.save(saveToForexTable(element));
					session.save(saveToBanknoteTable(element));
					session.save(saveToCrossTable(element));
					session.save(saveToInformationTable(element));

					transaction.commit();
				} catch (Exception e) {
					transaction.rollback();
					throw e;
				}
			}
		}
	}

	protected Forex saveToForexTable(Element element) {

		Forex forex = new Forex();
		LocalDate date = FileModificationDate.getXMLDate("output.xml");
		
		forex.setCreateDate(date);
		forex.setCurrencyCode(element.getAttribute("CurrencyCode"));
		forex.setUnit(Integer.parseInt(getValueOfElement(element, "Unit")));
		forex.setForexBuying(Double.parseDouble(getValueOfElement(element, "ForexBuying")));
		forex.setForexSelling(Double.parseDouble(getValueOfElement(element, "ForexSelling")));
		return forex;
	}

	protected Banknote saveToBanknoteTable(Element element) {

		Banknote banknote = new Banknote();
		LocalDate date = FileModificationDate.getXMLDate("output.xml");

		banknote.setCreateDate(date);
		banknote.setCurrencyCode(element.getAttribute("CurrencyCode"));
		banknote.setUnit(Integer.parseInt(getValueOfElement(element, "Unit")));
		banknote.setBanknoteBuying(Double.parseDouble(getValueOfElement(element, "BanknoteBuying")));
		banknote.setBanknoteSelling(Double.parseDouble(getValueOfElement(element, "BanknoteSelling")));

		return banknote;
	}

	protected Cross saveToCrossTable(Element element) {

		Cross cross = new Cross();
		LocalDate date = FileModificationDate.getXMLDate("output.xml");

		double crossRate = Double.parseDouble(getValueOfElement(element, "CrossRateUSD"));
		if (crossRate == 0) {
			crossRate = Double.parseDouble(getValueOfElement(element, "CrossRateOther"));
		}
		cross.setCreateDate(date);
		cross.setCurrencyCode(element.getAttribute("CurrencyCode"));
		cross.setUnit(Integer.parseInt(getValueOfElement(element, "Unit")));
		cross.setCrossRate(crossRate);

		return cross;
	}

	protected Information saveToInformationTable(Element element) {

		Information information = new Information();
		LocalDate date = FileModificationDate.getXMLDate("output.xml");

		information.setCreateDate(date);
		information.setInformationRate(Double.parseDouble(getValueOfElement(element, "InformationRate")));
		information.setUnit(Integer.parseInt(getValueOfElement(element, "Unit")));
		information.setCurrencyCode(element.getAttribute("CurrencyCode"));

		return information;
	}

	private static String getValueOfElement(Element parentElement, String label) {
		Element requiredElement = (Element) parentElement.getElementsByTagName(label).item(0);

		if (requiredElement != null) {
			Node child = requiredElement.getFirstChild();

			if (child instanceof CharacterData) {
				CharacterData cd = (CharacterData) child;
				String data = cd.getData().trim(); // Boşlukları temizle

				return data.isEmpty() ? "0" : data;
			}
		}

		return "0";
	}

}
