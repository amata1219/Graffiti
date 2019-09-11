package graffiti;

public class VectorRotation {

	/*
	 *	Vec3d v, axis
		center = dot(v, axis) * axis
		p = v - center
		q = cross(axis, p)
		rotated =  center + (p * cosθ) + (q + sinθ)
	 */

	public static final Vector3d AXIS_Y = Vector3d.at(0, 1, 0);

	public static void main(String[] $){
		//回転の中心となる座標
		Vector3d center = Vector3d.at(0, 0, 0);

		//パーティクルの初期座標
		Vector3d particle = Vector3d.at(1, 0, 0);

		//中心からの相対座標
		Vector3d relative = particle.subtract(center);

		//回転後のベクトル
		Vector3d rotated = rotate(relative, AXIS_Y, 10);

		//回転後のパーティクル
		Vector3d next = rotated.add(center);
	}

	/*
	 * @param v 回転させるベクトル
	 * @param axis 回転の軸ベクトル(要正規化)
	 * @return 回転されたベクトル
	 */
	public static Vector3d rotate(Vector3d v, Vector3d axis, double angle){
		//vを軸に射影し回転円の中心を得る
		Vector3d center = axis.multiply(v.dot(axis));

		//v - center
		Vector3d p = v.subtract(center);

		//軸とpに対する直交ベクトル得る
		Vector3d q = axis.cross(p);

		double rad = Math.toRadians(angle);

		//center + (p + cos(rad)) + (q + sin(rad))
		double s = Math.cos(rad);
		double t = Math.sin(rad);

		return center.add(p.multiply(s)).add(q.multiply(t));
	}

	public static void p(Vector3d v){
		System.out.println(v.toString());
	}

	//不変のベクトル
	public static class Vector3d {

		public static Vector3d at(double x, double y, double z){
			return new Vector3d(x, y, z);
		}

		public final double x, y, z;

		public Vector3d(double x, double y, double z){
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public Vector3d add(Vector3d v){
			double x = this.x + v.x;
			double y = this.y + v.y;
			double z = this.z + v.z;
			return Vector3d.at(x, y, z);
		}

		public Vector3d subtract(Vector3d v) {
			double x = this.x - v.x;
			double y = this.y - v.y;
			double z = this.z - v.z;
			return Vector3d.at(x, y, z);
		}

		public Vector3d multiply(double m) {
			double x = this.x * m;
			double y = this.y * m;
			double z = this.z * m;
			return Vector3d.at(x, y, z);
		}

		public double dot(Vector3d v){
			return this.x * v.x + this.y * v.y + this.z * v.z;
		}

		public Vector3d cross(Vector3d v) {
			double x = this.y * v.z - (v.y * this.z);
			double y = this.z * v.x - (v.z * this.x);
			double z = this.x * v.y - (v.x * this.y);
			return Vector3d.at(x, y, z);
		}

		@Override
		public String toString(){
			return x + ", " + y + ", " + z;
		}

	}

}
