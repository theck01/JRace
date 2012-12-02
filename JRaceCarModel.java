
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.*;

/*
3d model of a car object
*/
public class JRaceCarModel extends BranchGroup{
	
	public JRaceCarModel(int r, int g, int b){		
		
		super();
		
		//Materials and appearances
		Material car_mat = new Material();
		car_mat.setDiffuseColor((float)r/255, (float)g/255, (float)b/255);
		car_mat.setAmbientColor((float)JRaceConstants.ambient*(r/255), (float)JRaceConstants.ambient*(g/255), (float)JRaceConstants.ambient*(b/255));
		Appearance car_body = new Appearance();
		car_body.setMaterial(car_mat);
		
		Material window_mat = new Material();
		window_mat.setDiffuseColor(0.0f, 1.0f, 1.0f);
		window_mat.setAmbientColor(0.0f, JRaceConstants.ambient*1.0f, JRaceConstants.ambient*1.0f);
		Appearance windshield = new Appearance();
		windshield.setMaterial(window_mat);
		
		Material wheel_mat = new Material();
		wheel_mat.setDiffuseColor(0.0f, 0.0f, 0.0f);
		wheel_mat.setAmbientColor(0.0f, 0.0f, 0.0f);
		Appearance wheels = new Appearance();
		wheels.setMaterial(wheel_mat);
		
		Material brake_mat = new Material();
		brake_mat.setDiffuseColor(0.7f, 0.0f, 0.0f);
		brake_mat.setAmbientColor(JRaceConstants.ambient*0.7f, 0.0f, 0.0f);
		Appearance brakes = new Appearance();
		brakes.setMaterial(brake_mat);
		
		//Vectors							  
		Vector3f trunk_normal = new Vector3f(-0.6f, 0.8f, 0.0f);
		Vector3f hood_normal = new Vector3f(0.5f,(5.0f/3.0f), 0.0f);
		Vector3f windshield_normal = new Vector3f(1.0f, 2.0f, 0.0f);
		hood_normal.normalize();
		windshield_normal.normalize();
		
		//Car body		
		Transform3D body_trans = new Transform3D();
		body_trans.setTranslation(new Vector3d(0.0,0.375,0.0));
		TransformGroup body_group = new TransformGroup(body_trans);
		this.addChild(body_group);
		Box base = new Box(2.0f, 0.125f, 0.9f, Box.GENERATE_NORMALS, car_body);
		body_group.addChild(base);
		
		Transform3D door_trans = new Transform3D();
		door_trans.setTranslation(new Vector3d(-((double)1/6), 0.75, 0.0));
		TransformGroup door_group = new TransformGroup(door_trans);
		this.addChild(door_group);
		Box door = new Box(0.5f, 0.25f, 0.9f, Box.GENERATE_NORMALS, car_body);
		door_group.addChild(door);
		
		
		//Trunk faces
		//Counter clockwise form front
		TriangleArray trunk_right = new TriangleArray(3, TriangleArray.COORDINATES | TriangleArray.NORMALS);
		trunk_right.setCoordinate(0, new Point3d(-2.0, 0.5, 0.9));
		trunk_right.setCoordinate(1, new Point3d(-((double)2)/3, 0.5, 0.9));
		trunk_right.setCoordinate(2, new Point3d(-((double)2)/3, 1.5, 0.9));
		for(int i=0; i<3; i++){
			trunk_right.setNormal(i, new Vector3f(0.0f, 0.0f, 1.0f));
		}
		this.addChild(new Shape3D(trunk_right, car_body));
		
		TriangleArray trunk_left = new TriangleArray(3, TriangleArray.COORDINATES | TriangleArray.NORMALS);
		trunk_left.setCoordinate(0, new Point3d(-2.0, 0.5, -0.9));
		trunk_left.setCoordinate(1, new Point3d(-((double)2)/3, 1.5, -0.9));
		trunk_left.setCoordinate(2, new Point3d(-((double)2)/3, 0.5, -0.9));
		for(int i=0; i<3; i++){
			trunk_left.setNormal(i, new Vector3f(0.0f, 0.0f, -1.0f));
		}
		this.addChild(new Shape3D(trunk_left, car_body));
		
		QuadArray trunk_top = new QuadArray(4, QuadArray.COORDINATES | QuadArray.NORMALS);
		trunk_top.setCoordinate(0, new Point3d(-2.0, 0.5, -0.9));
		trunk_top.setCoordinate(1, new Point3d(-2.0, 0.5, 0.9));
		trunk_top.setCoordinate(2, new Point3d(-((double)2)/3, 1.5, 0.9));
		trunk_top.setCoordinate(3, new Point3d(-((double)2)/3, 1.5, -0.9));
		for(int i=0; i<4; i++){
			trunk_top.setNormal(i, trunk_normal);
		}
		this.addChild(new Shape3D(trunk_top, car_body));
		
		//Hood faces
		TriangleArray hood_right = new TriangleArray(3, TriangleArray.COORDINATES | TriangleArray.NORMALS);
		hood_right.setCoordinate(0, new Point3d(((double)1)/3, 0.5, 0.9));
		hood_right.setCoordinate(1, new Point3d(2.0, 0.5, 0.9));
		hood_right.setCoordinate(2, new Point3d(((double)1)/3, 1.0, 0.9));
		for(int i=0; i<3; i++){
			hood_right.setNormal(i, new Vector3f(0.0f, 0.0f, 1.0f));
		}
		this.addChild(new Shape3D(hood_right, car_body));
		
		TriangleArray hood_left = new TriangleArray(3, TriangleArray.COORDINATES | TriangleArray.NORMALS);
		hood_left.setCoordinate(0, new Point3d(((double)1)/3, 0.5, -0.9));
		hood_left.setCoordinate(1, new Point3d(((double)1)/3, 1.0, -0.9));
		hood_left.setCoordinate(2, new Point3d(2.0, 0.5, -0.9));
		for(int i=0; i<3; i++){
			hood_left.setNormal(i, new Vector3f(0.0f, 0.0f, -1.0f));
		}
		this.addChild(new Shape3D(hood_left, car_body));
		
		QuadArray hood_top = new QuadArray(4, QuadArray.COORDINATES | QuadArray.NORMALS);
		hood_top.setCoordinate(0, new Point3d(((double)1)/3, 1.0, -0.9));
		hood_top.setCoordinate(1, new Point3d(((double)1)/3, 1.0, 0.9));
		hood_top.setCoordinate(2, new Point3d(2.0, 0.5, 0.9));
		hood_top.setCoordinate(3, new Point3d(2.0, 0.5, -0.9));
		for(int i=0; i<4; i++){
			hood_top.setNormal(i, hood_normal);
		}
		this.addChild(new Shape3D(hood_top, car_body));
		
		//Windshield Faces
		TriangleArray wind_right = new TriangleArray(3, TriangleArray.COORDINATES | TriangleArray.NORMALS);
		wind_right.setCoordinate(0, new Point3d(((double)1)/3, 1, 0.9));
		wind_right.setCoordinate(1, new Point3d((-(double)2)/3, 1.5, 0.9));
		wind_right.setCoordinate(2, new Point3d((-(double)2)/3, 1, 0.9));
		for(int i=0; i<3; i++){
			wind_right.setNormal(i, new Vector3f(0.0f, 0.0f, 1.0f));
		}
		this.addChild(new Shape3D(wind_right, windshield));
		
		TriangleArray wind_left = new TriangleArray(3, TriangleArray.COORDINATES | TriangleArray.NORMALS);
		wind_left.setCoordinate(0, new Point3d(((double)1)/3, 1, -0.9));
		wind_left.setCoordinate(1, new Point3d((-(double)2)/3, 1, -0.9));
		wind_left.setCoordinate(2, new Point3d((-(double)2)/3, 1.5, -0.9));
		for(int i=0; i<3; i++){
			wind_left.setNormal(i, new Vector3f(0.0f, 0.0f, -1.0f));
		}
		this.addChild(new Shape3D(wind_left, windshield));
		
		QuadArray wind_top = new QuadArray(4, QuadArray.COORDINATES | QuadArray.NORMALS);
		wind_top.setCoordinate(0, new Point3d(((double)1)/3, 1, -0.9));
		wind_top.setCoordinate(1, new Point3d((-(double)2)/3, 1.5, -0.9));
		wind_top.setCoordinate(2, new Point3d((-(double)2)/3, 1.5, 0.9));
		wind_top.setCoordinate(3, new Point3d(((double)1)/3, 1, 0.9));
		for(int i=0; i<4; i++){
			wind_top.setNormal(i, windshield_normal);
		}
		this.addChild(new Shape3D(wind_top, windshield));
		
		//Wheel 1		
		Transform3D wheel_translate = new Transform3D();
		wheel_translate.setTranslation(new Vector3d(-1.25, 0.35, 0.9));
		TransformGroup wheel_translate_group = new TransformGroup(wheel_translate);
		
		Cylinder wheel = new Cylinder(0.35f, 0.25f, Cylinder.GENERATE_NORMALS, wheels);
		
		Transform3D wheel_rotate = new Transform3D();
		wheel_rotate.rotX(Math.toRadians(90));
		TransformGroup wheel_rotate_group = new TransformGroup(wheel_rotate);
		
		wheel_rotate_group.addChild(wheel);
		wheel_translate_group.addChild(wheel_rotate_group);
		this.addChild(wheel_translate_group);
		
		//Wheel 2
		wheel_translate = new Transform3D();
		wheel_translate.setTranslation(new Vector3d(-1.25, 0.35, -0.9));
		wheel_translate_group = new TransformGroup(wheel_translate);
		
		wheel = new Cylinder(0.35f, 0.25f, Cylinder.GENERATE_NORMALS, wheels);
		
		wheel_rotate = new Transform3D();
		wheel_rotate.rotX(Math.toRadians(90));
		wheel_rotate_group = new TransformGroup(wheel_rotate);
		
		wheel_rotate_group.addChild(wheel);
		wheel_translate_group.addChild(wheel_rotate_group);
		this.addChild(wheel_translate_group);
		
		//Wheel 3
		wheel_translate = new Transform3D();
		wheel_translate.setTranslation(new Vector3d(1.25, 0.35, 0.9));
		wheel_translate_group = new TransformGroup(wheel_translate);
		
		wheel = new Cylinder(0.35f, 0.25f, Cylinder.GENERATE_NORMALS, wheels);
		
		wheel_rotate = new Transform3D();
		wheel_rotate.rotX(Math.toRadians(90));
		wheel_rotate_group = new TransformGroup(wheel_rotate);
		
		wheel_rotate_group.addChild(wheel);
		wheel_translate_group.addChild(wheel_rotate_group);
		this.addChild(wheel_translate_group);
		
		//Wheel 4
		wheel_translate = new Transform3D();
		wheel_translate.setTranslation(new Vector3d(1.25, 0.35, -0.9));
		wheel_translate_group = new TransformGroup(wheel_translate);
		
		wheel = new Cylinder(0.35f, 0.25f, Cylinder.GENERATE_NORMALS, wheels);
		
		wheel_rotate = new Transform3D();
		wheel_rotate.rotX(Math.toRadians(90));
		wheel_rotate_group = new TransformGroup(wheel_rotate);
		
		wheel_rotate_group.addChild(wheel);
		wheel_translate_group.addChild(wheel_rotate_group);
		this.addChild(wheel_translate_group);
		
		//Brake lights
		Transform3D brake_trans = new Transform3D();
		brake_trans.setTranslation(new Vector3f(-2.0f, 0.4f, -0.8f));
		TransformGroup brake_group = new TransformGroup(brake_trans);
		this.addChild(brake_group);
		Box left_light = new Box(0.025f, 0.075f, 0.1f, Box.GENERATE_NORMALS, brakes);
		brake_group.addChild(left_light);
		
		brake_trans = new Transform3D();
		brake_trans.setTranslation(new Vector3f(-2.0f, 0.4f, 0.8f));
		brake_group = new TransformGroup(brake_trans);
		this.addChild(brake_group);
		Box right_light = new Box(0.025f, 0.075f, 0.1f, Box.GENERATE_NORMALS, brakes);
		brake_group.addChild(right_light);						   						  
	}
}
