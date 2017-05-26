// AI Lift-Create :: Auto-generated code
// Total components in project: 8

function main()
{
	var rectangle_0 = CAG.rectangle({center: [-3, 600], radius: [2, 600]});
	var rectangle_1 = CAG.rectangle({center: [600, -3], radius: [600, 2]});
	var rectangle_2 = CAG.rectangle({center: [1202, 600], radius: [2, 600]});
	var rectangle_3 = CAG.rectangle({center: [600, 1202], radius: [600, 2]});
	var rectangle_4 = CAG.rectangle({center: [152, 447], radius: [152, 370]});
	var rectangle_5 = CAG.rectangle({center: [757, 532], radius: [427, 467]});
	var rectangle_6 = CAG.rectangle({center: [597, 1012], radius: [558, 37]});
	var rectangle_7 = CAG.rectangle({center: [600, 1140], radius: [509, 60]});

	var merge = union(rectangle_0, rectangle_1, rectangle_2, rectangle_3, rectangle_4, rectangle_5, rectangle_6, rectangle_7, rectangle_7);

	return merge.scale(0.01);
}
