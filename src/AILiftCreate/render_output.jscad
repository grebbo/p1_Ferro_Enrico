// AI Lift-Create :: Auto-generated code
// Total Stefano_package.components in project: 7

function main()
{
	var csgrectangle_0 = new CSG.Path2D([[-5, 0], [0, 0], [0, 1200], [-5, 1200]], true);
	var rectangle_0 = csgrectangle_0.rectangularExtrude(0.5, 0.5, 70, false);
	var csgrectangle_1 = new CSG.Path2D([[0, -5], [1200, -5], [1200, 0], [0, 0]], true);
	var rectangle_1 = csgrectangle_1.rectangularExtrude(0.5, 0.5, 70, false);
	var csgrectangle_2 = new CSG.Path2D([[1200, 0], [1205, 0], [1205, 1200], [1200, 1200]], true);
	var rectangle_2 = csgrectangle_2.rectangularExtrude(0.5, 0.5, 70, false);
	var csgrectangle_3 = new CSG.Path2D([[0, 1200], [1200, 1200], [1200, 1205], [0, 1205]], true);
	var rectangle_3 = csgrectangle_3.rectangularExtrude(0.5, 0.5, 70, false);
	var csgrectangle_4 = new CSG.Path2D([[0, 77], [305, 77], [305, 817], [0, 817]], true);
	var rectangle_4 = csgrectangle_4.rectangularExtrude(0.5, 0.5, 70, false);
	var csgrectangle_5 = new CSG.Path2D([[330, 65], [1185, 65], [1185, 1000], [330, 1000]], true);
	var rectangle_5 = csgrectangle_5.rectangularExtrude(0.5, 0.5, 70, false);
	var csgrectangle_7 = new CSG.Path2D([[91, 1080], [1110, 1080], [1110, 1200], [91, 1200]], true);
	var rectangle_7 = csgrectangle_7.rectangularExtrude(0.5, 0.5, 70, false);

	var merge = union(rectangle_0, rectangle_1, rectangle_2, rectangle_3, rectangle_4, rectangle_5, rectangle_7, rectangle_7);

	return merge.scale(0.5);
}
