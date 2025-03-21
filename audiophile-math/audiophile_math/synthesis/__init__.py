import open3d as o3d
import numpy as np

class VibratingPointCloud:
    def __init__(self, neighbor_distance = 0.2):
        self.points = []
        self.neighbor_distance = neighbor_distance
    def add_point(self, point):
        self.points.append(point)
    def finish(self):
        for point in self.points:
            for other_point in self.points:
                if point != other_point:
                    if np.linalg.norm(point.location - other_point.location) < self.neighbor_distance:
                        point.connect_to(other_point)
class VibratingPoint:
    def __init__(self, location, material = None):
        self.location = location
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