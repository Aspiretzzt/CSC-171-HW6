import java.util.HashMap;
import java.util.Map;
import java.util.Collection;
import java.util.Scanner;

public class SupplyChainHW {

	public static double profitCalculation(double Mass, double Distance, double Payment,
			Map<String, Fleet> vehicleMap) {
		double maxProfit = -1.0;
		String bestVehicle = " ";
		for (Map.Entry<String, Fleet> entry : vehicleMap.entrySet()) {
			if (entry.getValue().range >= Distance) {
				if (entry.getValue().maxCargo >= Mass) {
					double time = Distance / entry.getValue().speed;
					if (maxProfit < Payment - time * (entry.getValue().hourCost)
							- Distance * entry.getValue().fuelBurn) {
						maxProfit = Payment - time * (entry.getValue().hourCost) - Distance * entry.getValue().fuelBurn;
						bestVehicle = entry.getKey();
					}
				}
			}
		}
		if (maxProfit > 0) {
			System.out.printf("%s %.2f\n", bestVehicle, maxProfit);
			return maxProfit;
		} else {
			System.out.println("decline");
			return 0.0;

		}

	}

	public static void main(String[] args) {
		Double TotalProfit = 0.0;
		Map<String, Fleet> vehicleMap = new HashMap<>();
		Scanner input = new Scanner(System.in);
		int numberOFVehicles = input.nextInt();
		input.nextLine();
		for (int i = 0; i < numberOFVehicles; i++) {
			String line = input.nextLine();
			Scanner lineScanner = new Scanner(line);
			String name = lineScanner.next();
			Fleet fleet = new Fleet(lineScanner.nextDouble(), lineScanner.nextDouble(), lineScanner.nextDouble(),
					lineScanner.nextDouble(), lineScanner.nextDouble(), lineScanner.nextDouble());
			vehicleMap.put(name, fleet);
		}
		while (input.hasNextLine()) {
			String line = input.nextLine();
			Scanner lineScanner = new Scanner(line);
			String Masstest = lineScanner.next();
			if (Masstest.equals("quit") == false) {
				double Mass = Double.parseDouble(Masstest);
				double Distance = lineScanner.nextDouble();
				double Payment = lineScanner.nextDouble();
				TotalProfit += profitCalculation(Mass, Distance, Payment, vehicleMap);
			} else {
				System.out.printf("TotalProfit: %.2f\n", TotalProfit);
				break;
			}
		}
	}
}

class Fleet {
	public double mass;
	public double maxCargo;
	public double range;
	public double speed;
	public double hourCost;
	public double fuelBurn;

	public Fleet(double mass, double maxCargo, double range, double speed, double hourCost, double fuelBurn) {
		this.mass = mass;
		this.maxCargo = maxCargo;
		this.range = range;
		this.speed = speed;
		this.hourCost = hourCost;
		this.fuelBurn = fuelBurn;
	}
}
