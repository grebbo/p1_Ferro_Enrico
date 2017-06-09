// AI Lift-Create :: Auto-generated code
// Total Stefano_package.components in project: 14

function main()
{
	var rectangle_0 = difference(CAG.rectangle({center: [-3.0, 600.0], radius: [2.0, 600.0]}), CAG.rectangle({center: [-3.0, 600.0], radius: [1.5, 599.5]})).extrude({offset: [0, 0, 750.0]});

	var rectangle_1 = difference(CAG.rectangle({center: [600.0, -3.0], radius: [600.0, 2.0]}), CAG.rectangle({center: [600.0, -3.0], radius: [599.5, 1.5]})).extrude({offset: [0, 0, 750.0]});

	var rectangle_2 = difference(CAG.rectangle({center: [1202.0, 600.0], radius: [2.0, 600.0]}), CAG.rectangle({center: [1202.0, 600.0], radius: [1.5, 599.5]})).extrude({offset: [0, 0, 750.0]});

	var rectangle_3 = difference(CAG.rectangle({center: [600.0, 1202.0], radius: [600.0, 2.0]}), CAG.rectangle({center: [600.0, 1202.0], radius: [599.5, 1.5]})).extrude({offset: [0, 0, 750.0]});

	var rectangle_4 = difference(CAG.rectangle({center: [152.0, 515.0], radius: [152.0, 438.0]}), CAG.rectangle({center: [152.0, 515.0], radius: [151.5, 437.5]})).extrude({offset: [0, 0, 1.0]});

	var rectangle_5 = difference(CAG.rectangle({center: [125.0, 107.0], radius: [125.0, 30.0]}), CAG.rectangle({center: [125.0, 107.0], radius: [124.5, 29.5]})).extrude({offset: [0, 0, 1.0]});

	var rectangle_6 = difference(CAG.rectangle({center: [125.0, 923.0], radius: [125.0, 30.0]}), CAG.rectangle({center: [125.0, 923.0], radius: [124.5, 29.5]})).extrude({offset: [0, 0, 1.0]});

	var rectangle_7 = difference(CAG.rectangle({center: [150.0, 141.0], radius: [35.0, 4.0]}), CAG.rectangle({center: [150.0, 141.0], radius: [34.5, 3.5]})).extrude({offset: [0, 0, 1.0]});

	var rectangle_8 = difference(CAG.rectangle({center: [150.0, 180.0], radius: [4.0, 35.0]}), CAG.rectangle({center: [150.0, 180.0], radius: [3.5, 34.5]})).extrude({offset: [0, 0, 1.0]});

	var rectangle_9 = difference(CAG.rectangle({center: [150.0, 889.0], radius: [35.0, 4.0]}), CAG.rectangle({center: [150.0, 889.0], radius: [34.5, 3.5]})).extrude({offset: [0, 0, 1.0]});

	var rectangle_10 = difference(CAG.rectangle({center: [150.0, 850.0], radius: [4.0, 35.0]}), CAG.rectangle({center: [150.0, 850.0], radius: [3.5, 34.5]})).extrude({offset: [0, 0, 1.0]});

	var rectangle_11 = difference(CAG.rectangle({center: [732.0, 507.0], radius: [402.0, 442.0]}), CAG.rectangle({center: [732.0, 507.0], radius: [401.5, 441.5]})).extrude({offset: [0, 0, 1.0]});

	var rectangle_12 = difference(CAG.rectangle({center: [597.0, 1012.0], radius: [558.0, 37.0]}), CAG.rectangle({center: [597.0, 1012.0], radius: [557.5, 36.5]})).extrude({offset: [0, 0, 1.0]});

	var rectangle_13 = difference(CAG.rectangle({center: [600.0, 1140.0], radius: [509.0, 60.0]}), CAG.rectangle({center: [600.0, 1140.0], radius: [508.5, 59.5]})).extrude({offset: [0, 0, 1.0]});


	var merge = union(rectangle_0, rectangle_1, rectangle_2, rectangle_3, rectangle_4, rectangle_5, rectangle_6, rectangle_7, rectangle_8, rectangle_9, rectangle_10, rectangle_11, rectangle_12, rectangle_13, rectangle_13);

	return merge.scale(0.1);
}
