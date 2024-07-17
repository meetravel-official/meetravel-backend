# MeeTravel Kubernetes

## [K8S] everything in eks-workspace cluster

eks-workspace cluster의 **선언적 관리**와 **GitOps** 를 위한 repository 입니다.

cluster 의 **현 상태를 정확하고 투명**하게 파악하며, **누가 언제 어떤 변경을 적용했는지 기록**하는 것을 목표로 합니다.

## Structures of Directory
### [`/apps/*`](https://github.com/wafflestudio/waffle-world/tree/main/apps)
- (업데이트 예정)

### [`/misc/*`](https://github.com/wafflestudio/waffle-world/tree/main/misc)
- admin 이 `kubectl`로 수동으로 관리하는 manifest 들이 위치합니다. load-balancer-controller, cluster-autoscaler, cert-manager 등 핵심적인 clsuter infra 와 관련되어 있습니다.

## Related Links (맞게 수정 예정)
### [argocd.wafflestudio.com](https://argocd.wafflestudio.com)
- Argo CD 가 관리하는 [`/apps/*`](https://github.com/wafflestudio/waffle-world/tree/main/apps) 의 Application 들을 모니터링, 관리할 수 있습니다.
- GitHub wafflestudio org 구성원들은 누구나 GitHub SSO 로 로그인 가능합니다.

## Related Repos & Blog Posts

### [wafflestudio / waffle-world](https://github.com/wafflestudio/waffle-world)

### [EKS 로 밑바닥부터 K8S cluster 구축하기 (1)](https://medium.com/wafflestudio/setup-k8s-cluster-with-aws-eks-in-snu-cse-dev-wafflestudio-1-91d620e66276)

---
# Deploy services on Kubernetes

In Kubernetes, the service consists of two components,

1. Environments
2. Applications

And you can choose the storage in the application either NFS or Cinder provided by Openstack(used by [Pasta](http://paas-ta.kr))

# Prerequisites

## Install kustomize

If you are using kubectl 1.14 or later, it embedded kustomize. So please ignore this section.
I recommend that you install the latest version of [kubectl](https://kubectl.docs.kubernetes.io/installation/kubectl/) for your cluster instead of installing kustomize.
Even so, if you want to use kustomize, refer to [official kustomize doc](https://kustomize.io/).

# Deploy service

You must follow deployment order.

## Deploy environments

```sh
$ kubectl apply -k k8s/environments
```

## Deploy applications

```sh
$ kubectl apply -k k8s/applications
```

# Shutdown service

Simply apply deployment in reverse order.

```sh
$ kubectl delete -k k8s/environments
$ kubectl delete -k k8s/applications
```
