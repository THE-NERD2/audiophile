import open3d as o3d
import numpy as np

class VibratingPointCloud:
    def __init__(self):
        self.points = []
    def add_point(self, point):
        self.points.append(point)
    def finish(self):
        pass # TODO: Link points together
class VibratingPoint:
    def __init__(self, point, material = None):
        self.point = point
        self.current_magnitude = 0
        self.material = material
        self.neighbors = []
    def connect_to(self, point):
        self.neighbors.append(point)

def create_cloud_from_model(model_path):
    mesh = o3d.io.read_triangle_mesh(model_path)
    mesh.compute_vertex_normals()
    area = mesh.get_surface_area()
    pcd = mesh.sample_points_poisson_disk(number_of_points = area * 9, init_factor = 3)
    point_array = np.asarray(pcd.points)
    vpc = VibratingPointCloud()
    for point in point_array:
        vpc.add_point(VibratingPoint(point))
    vpc.finish()
    return vpc