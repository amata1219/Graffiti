package graffiti;

import static graffiti.VectorRotation.ImmutableVector3d.*;

import org.bukkit.Location;

public class VectorRotation {

	/*
	 *	axisを軸にvをθだけ回転させる
	 *
	 *	Vec3d v, axis
	 *	center = dot(v, axis) * axis
	 *	p = v - center
	 *	q = cross(axis, p)
	 *	rotated =  center + (p * cosθ) + (q + sinθ)
	 *
	 */

	public static final ImmutableVector3d AXIS_X = at(1, 0, 0);
	public static final ImmutableVector3d AXIS_Y = at(0, 1, 0);
	public static final ImmutableVector3d AXIS_Z = at(0, 0, 1);

	//Y軸を軸としてベクトルを回転させる(サンプルコード)
	public static void main(String[] $){
		//回転の中心となる座標
		ImmutableVector3d center = at(0, 0, 0);

		//パーティクルの初期座標
		ImmutableVector3d particle = at(1, 0, 0);

		//中心からの相対座標
		ImmutableVector3d relative = particle.subtract(center);

		//回転後のベクトル
		ImmutableVector3d rotated = rotate(relative, AXIS_Y, 10);

		//回転後のパーティクルの座標
		ImmutableVector3d next = rotated.add(center);

		//各成分に10^-17程度の誤差を含んでいる可能性有り(無視出来るレベル)
		System.out.println(next.toString());
	}

	/*
	 * @param v 回転させるベクトル
	 * @param axis 軸となるベクトル(要正規化)
	 * @param angle 回転角
	 * @return 回転されたベクトル
	 */
	public static ImmutableVector3d rotate(ImmutableVector3d v, ImmutableVector3d axis, double angle){
		//vを軸に射影し回転円の中心を得る
		ImmutableVector3d center = axis.multiply(v.dot(axis));

		//v - center
		ImmutableVector3d p = v.subtract(center);

		//軸とpに対する直交ベクトル得る
		ImmutableVector3d q = axis.cross(p);

		double rad = Math.toRadians(angle);

		//center + (p + cos(rad)) + (q + sin(rad))
		double s = Math.cos(rad);
		double t = Math.sin(rad);

		return center.add(p.multiply(s)).add(q.multiply(t));
	}

	public static class ImmutableVector3d {

		public static ImmutableVector3d at(double x, double y, double z){
			return new ImmutableVector3d(x, y, z);
		}

		public static ImmutableVector3d at(Location loc){
			return at(loc.getX(), loc.getY(), loc.getZ());
		}

		public final double x, y, z;

		public ImmutableVector3d(double x, double y, double z){
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public double magnitude(){
			return Math.sqrt(x * x + y * y + z * z);
		}

		public ImmutableVector3d normalize(){
			double mag = magnitude();
			return at(x / mag, y / mag, z / mag);
		}

		public double distance(ImmutableVector3d v){
			double x = this.x - v.x;
			double y = this.y - v.y;
			double z = this.z - v.z;
			return Math.sqrt(x * x + y * y + z * z);
		}

		public ImmutableVector3d midpoint(ImmutableVector3d v){
			return at((this.x + v.x) / 2, (this.y + y) / 2, (this.z + z) / 2);
		}

		public ImmutableVector3d internallyDividingPoint(ImmutableVector3d v, double m, double n){
			double denom = m + n;
			return at((n * x + m * v.x) / denom, (n * y + m * v.y) / denom, (n * z + m * v.z) / denom);
		}

		public ImmutableVector3d externallyDividingPoint(ImmutableVector3d v, double m, double n){
			return internallyDividingPoint(v, m, -n);
		}

		public ImmutableVector3d add(ImmutableVector3d v){
			return at(x + v.x, y + v.y, z + v.z);
		}

		public ImmutableVector3d subtract(ImmutableVector3d v) {
			return at(x - v.x, y - v.y, z - v.z);
		}

		public ImmutableVector3d multiply(ImmutableVector3d v){
			return at(x * v.x, y * v.y, z * v.z);
		}

		public ImmutableVector3d multiply(double m) {
			return at(x * m, y * m, z * m);
		}

		public ImmutableVector3d divide(ImmutableVector3d v){
			return at(x / v.x, y / v.y, z / v.z);
		}

		public ImmutableVector3d divide(double m) {
			return at(x * m, y / m, z / m);
		}

		public double dot(ImmutableVector3d v){
			return x * v.x + y * v.y + z * v.z;
		}

		public ImmutableVector3d cross(ImmutableVector3d v) {
			return at(y * v.z - (v.y * z), z * v.x - (v.z * x), x * v.y - (v.x * y));
		}

		public double radians(ImmutableVector3d v){
			return dot(v) / (magnitude() * v.magnitude());
		}

		@Override
		public String toString(){
			return x + ", " + y + ", " + z;
		}

	}

}
